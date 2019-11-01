package m3._03codegen

import java.io._
import java.util.Scanner

import m3.Ast0._
import m3.{Builtin, Level, ThUtil}
import m3._02typecheck.TCMeta._
import m3._02typecheck.Utils

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

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
        self.contains("!") || self.contains("!=") || self.contains("==") ||
        self.contains("<") || self.contains(">") ||
        self.contains("+") || self.contains("-") ||
        self.contains("*") || self.contains("/") ||
        self.contains("|") || self.contains("&")) "\"" + self + "\"" else self
  }

  sealed trait IsUnion
  case object NoUnion extends IsUnion
  case object SimpleU extends IsUnion
  case class NullableU(vth: TypeHint) extends IsUnion

  implicit class ThIrExtension(self: TypeHint) {
    class RecursiveSelfRefEx extends Exception

    def isRefTypeRecursive(mctx: ModContext, stack: Seq[String] = Seq.empty): Boolean = self match {
      case th: ScalarTh =>
        if (stack.contains(th.name)) throw new RecursiveSelfRefEx

        Utils.typeDecl(mctx.level, th) match {
          case (_, sd: ScalarDecl) => sd.ref
          case (_, sd: StructDecl) =>
            if (sd.isBuiltinArray) {
              val elTh = th.params(0)
              sd.getBuiltinArrayLen == None || elTh.isRefTypeRecursive(mctx, stack)
            } else {
              val specMap = ThUtil.makeSpecMap(sd.params, th.params)
              sd.fields.exists { f =>

                ThUtil.spec(f.th, specMap).isRefTypeRecursive(mctx, stack :+ th.name)
              }
            }
          case (mod, ud: UnionDecl) =>
            ud.variants.foreach(v => v.isRefTypeRecursive(mctx.copy(modules = mod +: mctx.modules), stack :+ th.name))
            false
        }
      case sth: StructTh => sth.seq.exists(fth => fth.typeHint.isRefTypeRecursive(mctx, stack))
      case uth: UnionTh =>
        uth.seq.exists(v => v.isRefTypeRecursive(mctx, stack))
      case _: FnTh => false
      case other =>
        throw new RuntimeException(s"Internal compiler error: unexpected to see th: $other here")
    }

    def isRefType(mctx: ModContext): Boolean =
      try {
        isRefTypeRecursive(mctx, Seq.empty)
      } catch {
        case _: RecursiveSelfRefEx => true
      }

    def isValueType(mctx: ModContext): Boolean =
      !isRefType(mctx)


    def classify(mctx: ModContext): TypeClass = {
      val isValue = self.isValueType(mctx)

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
          Utils.typeDecl(mctx.level, sth) match {
            case (_, sd: StructDecl) =>
              val specMap = ThUtil.makeSpecMap(sd.params, sth.params)
              val fields = sd.fields.map(f => FieldTh(f.name, ThUtil.spec(f.th, specMap)))

              if (isValue) ValueStruct(fields) else RefStruct(fields)
            case (_, ud: UnionDecl) =>
              sieveVariants(ud.variants.map(v => v))
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

    def isUnion(mctx: ModContext): IsUnion = {
      def sieveVariants(variants: Seq[TypeHint]) =
        if (variants.length == 2 && variants.contains(Builtin.thNil)) {
          val variant = variants.find(th => th != Builtin.thNil).get
          if (variant.isValueType(mctx)) SimpleU
          else NullableU(variant)
        }
        else SimpleU

      self match {
        case sth: ScalarTh =>
          Utils.typeDecl(mctx.level, sth) match {
            case (_, ud: UnionDecl) => sieveVariants(ud.variants.map(v => v))
            case _ => NoUnion
          }
        case uth: UnionTh => sieveVariants(uth.seq)
        case _ => NoUnion
      }
    }

    def asUnion(mctx: ModContext): Seq[TypeHint] =
      self match {
        case sth: ScalarTh =>
          Utils.typeDecl(mctx.level, sth) match {
            case (_, ud: UnionDecl) =>
              ud.variants.map(th => th)
            case _ =>
              throw new RuntimeException("unreachable")
          }
        case uth: UnionTh => uth.seq
        case _ => throw new RuntimeException("unreachable")
      }

    //FIXME: clash with TC.isArray
    def isIrArray(mctx: ModContext): Option[StructDecl] =
      self match {
        case sth: ScalarTh =>
          Utils.typeDecl(mctx.level, sth) match {
            case (_, sd: StructDecl) if sd.isBuiltinArray => Some(sd)
            case _ => None
          }
        case _ => None
      }

    def isStruct(mctx: ModContext): Boolean =
      self match {
        case sth: ScalarTh =>
          Utils.typeDecl(mctx.level, sth) match {
            case (_, _: StructDecl) => true
            case _ => false
          }
        case _: StructTh => true
        case _ => false
      }

    def structFields(mctx: ModContext): Seq[FieldTh] = self match {
      case sth: ScalarTh =>
        Utils.typeDecl(mctx.level, sth) match {
          case (_, sd: StructDecl) if !sd.isBuiltinArray =>
            val specMap = ThUtil.makeSpecMap(sd.params, sth.params)
            sd.fields.map(f => FieldTh(f.name, ThUtil.spec(f.th, specMap)))
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
          Utils.typeDecl(level, sth) match {
            case (_, _: ScalarDecl) => true
            case _ => false
          }
        case _ => false
      }

    def orderTypeHints(level: Level, module: Module, result: ArrayBuffer[TypeHint]): Unit =
      orderTypeHintsRec(level, module, result, Seq.empty)

    def orderTypeHintsRec(level: Level, module: Module, result: ArrayBuffer[TypeHint], stack: Seq[(Module, TypeHint)]): Unit = {
      if (stack.contains((module, self))) {
        // result += self //???
        return
      }

      self match {
        case th: ScalarTh =>
          Utils.typeDecl(level, th) match {
            case (_, sd: ScalarDecl) =>
              th.params.foreach(th => th.orderTypeHintsRec(level, module, result, stack :+ (module, self)))
            case (_, sd: StructDecl) if sd.isBuiltinArray =>
              val elementTh = th.params(0)
              elementTh.orderTypeHintsRec(level, module, result, stack)
            case (_, sd: StructDecl) =>
              val specMap = ThUtil.makeSpecMap(sd.params, th.params)
              sd.fields.foreach { f =>
                val specked = ThUtil.spec(f.th, specMap)
                specked.orderTypeHintsRec(level, module, result, stack :+ (module, self))
              }
            case (_, ud: UnionDecl) =>
              ud.variants.foreach(th => th.orderTypeHintsRec(level, module, result, stack :+ (module, self)))
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


    def toValueRec(mctx: ModContext): String = self match {
      case sth: ScalarTh =>
        if (Builtin.isDeclaredBuiltIn(sth.name))
          sth.toString
        else {
          val (mod, _) = Utils.typeDecl(mctx.level, sth)
          mod.pkg + "." + sth.copy(ie = None).toString
        }
      case UnionTh(seq) =>
        seq.map {
          case unionTh: UnionTh => "(" + unionTh.toValueRec(mctx) + ")"
          case other => other.toValueRec(mctx)
        }.mkString(" | ")
      case StructTh(seq) =>
        seq.map(f => f.typeHint.toValueRec(mctx)).mkString("(", ", ", ")")
      case _ => self.toString
    }

    def toValue(mctx: ModContext, prefix: String = "%", suffix: String = ""): String = self match {
      case Builtin.thNil | Builtin.thUnreachable => "void"
      case other =>
        prefix + (other.toValueRec(mctx) + suffix).escaped
    }

    def toDecl(mctx: ModContext, typeDecls: ArrayBuffer[(TypeHint, String)]): Unit = {

      def forUnion(variants: Seq[TypeHint]) = {
        val size = calculateSize(mctx, variants, typeDecls)
        s"{i64, [$size x i8]}"
      }

      if (self != Builtin.thNil && self != Builtin.thUnreachable) {
        typeDecls +=
          ((self,
            s"""${self.toValue(mctx)} = type """ + (self.isUnion(mctx) match {
              case NullableU(vth) =>
                vth.classify(mctx) match {
                  case RefStruct(_) => vth.toValue(mctx, suffix = ".body") + "*"
                  case _ => vth.toValue(mctx)
                }
              case _ =>
                self match {
                  case th: ScalarTh =>
                    Utils.typeDecl(mctx.level, th) match {
                      case (_, sd: ScalarDecl) if Builtin.isDeclaredBuiltIn(sd.name) =>
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
                      case (_, sd: ScalarDecl) => sd.llType
                      case (_, sd: StructDecl) if sd.isBuiltinArray =>
                        val elementIr = th.params(0).toValue(mctx)
                        if (th.isRefType(mctx))
                          s"{i32, $elementIr*}"
                        else
                          s"[${sd.getBuiltinArrayLen.get} x $elementIr]"
                      case (_, sd: StructDecl) =>
                        val specMap = ThUtil.makeSpecMap(sd.params, th.params)
                        val fieldsIr = sd.fields.map { f => ThUtil.spec(f.th, specMap).toValue(mctx) }
                        if (th.isRefType(mctx))
                          s"${self.toValue(mctx, suffix = ".body")}*\n${self.toValue(mctx, suffix = ".body")} = type { ${fieldsIr.mkString(", ")} }" // crunch
                        else
                          s"{ ${fieldsIr.mkString(", ")} }"
                      case (_, ud: UnionDecl) =>
                        val variants = ud.variants.map(v => v)
                        forUnion(variants)
                    }
                  case sth: StructTh =>
                    val fieldsIr = sth.seq.map { f => f.typeHint.toValue(mctx) }
                    if (sth.isRefType(mctx))
                      s"${self.toValue(mctx, suffix = ".body")}*\n${self.toValue(mctx, suffix = ".body")} = type { ${fieldsIr.mkString(", ")} }" // crunch
                    else
                      s"{ ${fieldsIr.mkString(", ")} }"
                  case uth: UnionTh => forUnion(uth.seq)
                  case fth: FnTh =>
                    s"{ ${fth.ret.toValue(mctx)} (${(fth.args.map(th => AbiTh.toCallArg(mctx, th)) :+ "i8*").mkString(", ")})*, i8* }"
                }
            })))
      }
    }
  }

  def calculateSize(mctx: ModContext, variants: Seq[TypeHint], typeDecl: ArrayBuffer[(TypeHint, String)]): Long = {
    val fw = new FileWriter("/tmp/sizes.ll")
    typeDecl.foreach { case (th, decl) => fw.write(decl + "\n") }

    val filtered = variants.filter(th => th != Builtin.thNil && th != Builtin.thUnreachable && typeDecl.map(_._1).contains(th))

    filtered.foreach { th =>
      fw.write(s"@${th.toValue(mctx, prefix = "", suffix = "__union_sizeof")} = global {i64, ${th.toValue(mctx)}} undef\n")
    }

    if (filtered.isEmpty) return 8

    fw.close()
    val proc = Runtime.getRuntime.exec(Seq("sh", "-c", "llc-8 -filetype=obj /tmp/sizes.ll -o /tmp/sizes.o && nm -td -n -S /tmp/sizes.o").toArray)
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