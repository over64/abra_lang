package m3.codegen

import java.io._
import java.util.Scanner

import m3.parse.Ast0._
import m3.parse.Level
import m3.typecheck.TCMeta._
import m3.typecheck.Utils.ThExtension
import m3.typecheck.{Builtin, Utils}

import scala.collection.mutable

sealed trait TypeClass
case class NullableUnion(variant: TypeHint) extends TypeClass
case class RefUnion(variants: Seq[TypeHint]) extends TypeClass
case class ValueUnion(variants: Seq[TypeHint]) extends TypeClass

case class RefStruct(fields: Seq[FieldTh]) extends TypeClass
case class ValueStruct(fields: Seq[FieldTh]) extends TypeClass

case object RefScalar extends TypeClass
case object ValueScalar extends TypeClass


object IrUtils {
  implicit class RichString(self: String) {
    def escaped: String =
      if (self.contains(" ") || self.contains("[") ||
        self.contains("<") || self.contains(">") ||
        self.contains("+") || self.contains("-") ||
        self.contains("*") || self.contains("/") ||
        self.contains("|") || self.contains("&" )) "\"" + self + "\"" else self
  }

  sealed trait IsUnion
  case object NoUnion extends IsUnion
  case object SimpleU extends IsUnion
  case class NullableU(vth: TypeHint) extends IsUnion

  implicit class ThIrExtension(self: TypeHint) {
    class RecursiveSelfRefEx extends Exception

    def isRefTypeRecursive(level: Level, module: Module, stack: Seq[String] = Seq.empty): Boolean = self match {
      case th: ScalarTh =>
        if (stack.contains(th.name)) throw new RecursiveSelfRefEx

        Utils.resolveType(level, module, th) match {
          case (_, _, sd: ScalarDecl) => sd.ref
          case (ieSeq, mod, sd: StructDecl) =>
            if (sd.isBuiltinArray) {
              val elTh = th.params(0)
              sd.getBuiltinArrayLen == None || elTh.isRefTypeRecursive(level, mod, stack)
            } else {
              val specMap = Utils.makeSpecMap(sd.params, th.params)
              sd.fields.exists(f => f.th.spec(specMap).isRefTypeRecursive(level, mod, stack :+ th.name))
            }
          case (ieSeq, mod, ud: UnionDecl) =>
            ud.variants.foreach(v => v.isRefTypeRecursive(level, mod, stack :+ th.name))
            false
        }
      case sth: StructTh => sth.seq.exists(fth => fth.typeHint.isRefTypeRecursive(level, module, stack))
      case uth: UnionTh =>
        uth.seq.exists(v => v.isRefTypeRecursive(level, module, stack))
      case _: FnTh => false
      case other => throw new RuntimeException(s"Internal compiler error: unexpected to see th: $other here")
    }

    def isRefType(level: Level, module: Module): Boolean =
      try {
        isRefTypeRecursive(level, module, Seq.empty)
      } catch {
        case _: RecursiveSelfRefEx => true
      }

    def isValueType(level: Level, module: Module): Boolean =
      !isRefType(level, module)


    def classify(level: Level, module: Module): TypeClass = {
      val isValue = self.isValueType(level, module)

      def sieveVariants(variants: Seq[TypeHint]) =
        if (variants.length == 2 && variants.contains(Builtin.thNil)) {
          val variant = variants.find(th => th != Builtin.thNil).get
          if (isValue) ValueUnion(variants) else NullableUnion(variant)
        } else if (isValue)
          ValueUnion(variants)
        else
          RefUnion(variants)

      self match {
        case sth: ScalarTh =>
          Utils.resolveType(level, module, sth) match {
            case (ieSeq, _, sd: StructDecl) =>
              val specMap = Utils.makeSpecMap(sd.params, sth.params)
              val fields = sd.fields.map(f => FieldTh(f.name, f.th.moveToMod(ieSeq).spec(specMap)))

              if (isValue) ValueStruct(fields) else RefStruct(fields)
            case (ieSeq, _, ud: UnionDecl) =>
              sieveVariants(ud.variants.map(v => v.moveToMod(ieSeq)))
            case _ =>
              if (isValue) ValueScalar else RefScalar
          }
        case sth: StructTh =>
          if (isValue) ValueStruct(sth.seq) else RefStruct(sth.seq)
        case uth: UnionTh =>
          sieveVariants(uth.seq)
        case _ =>
          if (isValue) ValueScalar else RefScalar
      }
    }

    def isUnion(level: Level, module: Module): IsUnion = {
      def sieveVariants(variants: Seq[TypeHint]) =
        if (variants.length == 2 && variants.contains(Builtin.thNil)) {
          val variant = variants.find(th => th != Builtin.thNil).get
          if (variant.isValueType(level, module)) SimpleU
          else NullableU(variant)
        }
        else SimpleU

      self match {
        case sth: ScalarTh =>
          Utils.resolveType(level, module, sth) match {
            case (ieSeq, _, ud: UnionDecl) => sieveVariants(ud.variants.map(v => v.moveToMod(ieSeq)))
            case _ => NoUnion
          }
        case uth: UnionTh => sieveVariants(uth.seq)
        case _ => NoUnion
      }
    }

    def asUnion(level: Level, module: Module): Seq[TypeHint] =
      self match {
        case sth: ScalarTh =>
          Utils.resolveType(level, module, sth) match {
            case (ieSeq, _, ud: UnionDecl) =>
              ud.variants.map(th => th.moveToMod(ieSeq))
            case _ => throw new RuntimeException("unreachable")
          }
        case uth: UnionTh => uth.seq
        case _ => throw new RuntimeException("unreachable")
      }

    //FIXME: clash with TC.isArray
    def isIrArray(level: Level, module: Module): Option[StructDecl] =
      self match {
        case sth: ScalarTh =>
          Utils.resolveType(level, module, sth) match {
            case (_, _, sd: StructDecl) if sd.isBuiltinArray => Some(sd)
            case _ => None
          }
        case _ => None
      }

    def isStruct(level: Level, module: Module): Boolean =
      self match {
        case sth: ScalarTh =>
          Utils.resolveType(level, module, sth) match {
            case (_, _, _: StructDecl) => true
            case _ => false
          }
        case _: StructTh => true
        case _ => false
      }

    def structFields(level: Level, module: Module): Seq[FieldTh] = self match {
      case sth: ScalarTh =>
        Utils.resolveType(level, module, sth) match {
          case (ieSeq, _, sd: StructDecl) if !sd.isBuiltinArray =>
            val specMap = Utils.makeSpecMap(sd.params, sth.params)
            sd.fields.map(f => FieldTh(f.name, f.th.moveToMod(ieSeq).spec(specMap)))
          case _ =>
            throw new RuntimeException(s"Internal compiler error: $self is not struct type")
        }
      case sth: StructTh => sth.seq
      case _ =>
        throw new RuntimeException(s"Internal compiler error: $self is not struct type")
    }

    def isScalar(level: Level, module: Module): Boolean =
      self match {
        case sth: ScalarTh =>
          Utils.resolveType(level, module, sth) match {
            case (_, _, _: ScalarDecl) => true
            case _ => false
          }
        case _ => false
      }

    def toValue: String = self match {
      case Builtin.thNil | Builtin.thUnreachable => "void"
      case other => "%" + other.toString.escaped
    }

    def orderTypeHints(level: Level, module: Module, result: mutable.ListBuffer[TypeHint]): Unit =
      orderTypeHintsRec(level, module, result, Seq.empty)

    def orderTypeHintsRec(level: Level, module: Module, result: mutable.ListBuffer[TypeHint], stack: Seq[(Module, TypeHint)]): Unit = {
      if (stack.contains((module, self))) {
        // result += self //???
        return
      }

      self match {
        case th: ScalarTh =>
          Utils.resolveType(level, module, th) match {
            case (_, _, sd: ScalarDecl) =>
              th.params.foreach(th => th.orderTypeHintsRec(level, module, result, stack :+ (module, self)))
            case (ieSeq, _, sd: StructDecl) if sd.isBuiltinArray =>
              val elementTh = th.params(0)
              elementTh.orderTypeHintsRec(level, module, result, stack)
            case (ieSeq, _, sd: StructDecl) =>
              val specMap = Utils.makeSpecMap(sd.params, th.params)
              sd.fields.foreach { f =>
                val specked = f.th.moveToMod(ieSeq).spec(specMap)
                specked.orderTypeHintsRec(level, module, result, stack :+ (module, self))
              }
            case (ieSeq, _, ud: UnionDecl) =>
              ud.variants.foreach(th => th.moveToMod(ieSeq).orderTypeHintsRec(level, module, result, stack :+ (module, self)))
          }
        case sth: StructTh =>
          sth.seq.foreach(f => f.typeHint.orderTypeHintsRec(level, module, result, stack))
        case uth: UnionTh =>
          uth.seq.foreach(th => th.orderTypeHintsRec(level, module, result, stack))
        case fth: FnTh =>
          fth.ret.orderTypeHintsRec(level, module, result, stack)
          fth.args.foreach(th => th.orderTypeHintsRec(level, module, result, stack))
      }

      result += self
    }

    def toDecl(level: Level, module: Module,
               typeDecls: mutable.ListBuffer[(TypeHint, String)]): Unit = {

      def forUnion(variants: Seq[TypeHint]) = {
        val size = calculateSize(variants, typeDecls)
        s"{i64, [$size x i8]}"
      }

      if (self != Builtin.thNil && self != Builtin.thUnreachable) {
        typeDecls +=
          ((self,
            s"""%${self.toString.escaped} = type """ + (self.isUnion(level, module) match {
              case NullableU(_) => "i8*"
              case _ =>
                self match {
                  case th: ScalarTh =>
                    Utils.resolveType(level, module, th) match {
                      case (_, _, sd: ScalarDecl) if Builtin.isDeclaredBuiltIn(sd.name) =>
                        sd.name match {
                          case "Long" => "i64"
                          case "Int" => "i32"
                          case "Short" => "i16"
                          case "Byte" => "i8"
                          case "Double" => "double"
                          case "Float" => "float"
                          case "Bool" => "i8"
                          case "String" => "i8*"
                          case "None" | "Unreachable" =>
                            throw new RuntimeException("Internal compiler error: cannot make IR alias for void type")
                        }
                      case (_, _, sd: ScalarDecl) => sd.llType
                      case (_, _, sd: StructDecl) if sd.isBuiltinArray =>
                        val elementIr = th.params(0).toValue
                        if (th.isRefType(level, module))
                          s"$elementIr*"
                        else
                          s"{i64, [${sd.getBuiltinArrayLen.get} x $elementIr]}"
                      case (ieSeq, _, sd: StructDecl) =>
                        val specMap = Utils.makeSpecMap(sd.params, th.params)
                        val fieldsIr = sd.fields.map { f => f.th.moveToMod(ieSeq).spec(specMap).toValue }
                        if (th.isRefType(level, module))
                          s"%${(self.toString + ".body").escaped}*\n%${(self.toString + ".body").escaped} = type { ${fieldsIr.mkString(", ")} }" // crunch
                        else
                          s"{ ${fieldsIr.mkString(", ")} }"
                      case (ieSeq, _, ud: UnionDecl) =>
                        val variants = ud.variants.map(v => v.moveToMod(ieSeq))
                        forUnion(variants)
                    }
                  case sth: StructTh =>
                    val fieldsIr = sth.seq.map { f => f.typeHint.toValue }
                    if (sth.isRefType(level, module))
                      s"%${(self.toString + ".body").escaped}*\n%${(self.toString + ".body").escaped} = type { ${fieldsIr.mkString(", ")} }" // crunch
                    else
                      s"{ ${fieldsIr.mkString(", ")} }"
                  case uth: UnionTh => forUnion(uth.seq)
                  case fth: FnTh =>
                    s"{ ${fth.ret.toValue} (${(fth.args.map(th => th.toValue) :+ "i8*").mkString(", ")})*, i8* }"
                }
            })))
      }
    }
  }

  def calculateSize(variants: Seq[TypeHint], typeDecl: mutable.ListBuffer[(TypeHint, String)]): Long = {
    val fw = new FileWriter("/tmp/sizes.ll")
    typeDecl.foreach { case (th, decl) => fw.write(decl + "\n") }

    val filtered = variants.filter(th => th != Builtin.thNil && th != Builtin.thUnreachable && typeDecl.map(_._1).contains(th))

    filtered.foreach { th =>
      fw.write(s"@${(th + "__union_sizeof").escaped} = global {i64, %${th.toString.escaped}} undef\n")
    }

    if (filtered.isEmpty) return 8

    fw.close()
    val proc = Runtime.getRuntime.exec(Seq("sh", "-c", "llc-7 -filetype=obj /tmp/sizes.ll -o /tmp/sizes.o && nm -td -n -S /tmp/sizes.o").toArray)
    proc.waitFor()

    val err = new Scanner(proc.getErrorStream)
    while (err.hasNextLine)
      println(err.nextLine())

    val sc = new Scanner(proc.getInputStream)

    filtered.map { th =>
      val line = sc.nextLine()
      println(line)
      line.split(" ")(1).toLong
    }.max - 8
  }
}