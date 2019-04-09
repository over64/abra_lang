package m3.codegen

import java.io._
import java.util.Scanner

import m3.parse.Ast0._
import m3.parse.Level
import m3.typecheck.TCMeta._
import m3.typecheck.Utils.ThExtension
import m3.typecheck.{Builtin, Utils}

import scala.collection.mutable

object IrUtils {
  implicit class RichString(self: String) {
    def escaped: String =
      if (self.contains(" ") || self.contains("[")) "\"" + self + "\"" else self
  }

  implicit class ThIrExtension(self: TypeHint) {
    class RecursiveSelfRefEx extends Exception

    def isRefTypeRecursive(level: Level, module: Module, stack: Seq[String] = Seq.empty): Boolean = self match {
      case th: ScalarTh =>
        if (stack.contains(th.name)) throw new RecursiveSelfRefEx

        Utils.resolveType(level, module, th) match {
          case (_, _, sd: ScalarDecl) => sd.ref
          case (ieSeq, mod, sd: StructDecl) =>
            val specMap = Utils.makeSpecMap(sd.params, th.params)
            sd.fields.exists(f => f.th.spec(specMap).isRefTypeRecursive(level, mod, stack :+ th.name))
          case (ieSeq, mod, ud: UnionDecl) =>
            ud.variants.foreach(v => v.isRefTypeRecursive(level, mod, stack :+ th.name))
            false
        }
      case sth: StructTh => sth.seq.exists(fth => fth.typeHint.isRefTypeRecursive(level, module, stack))
      case uth: UnionTh =>
        uth.seq.foreach(v => v.isRefTypeRecursive(level, module, stack))
        false
      case _: FnTh => false
      case other => throw new RuntimeException(s"Internal compiler error: unexpected to see th: $other here")
    }

    def isRefType(level: Level, module: Module): Boolean =
      try {
        isRefTypeRecursive(level, module, Seq.empty)
      } catch {
        case _: RecursiveSelfRefEx => true
      }

    def toValue: String = self match {
      case Builtin.thNil | Builtin.thUnreachable => "void"
      case other => "%" + other.toString.escaped
    }

    def orderTypeHints(level: Level, module: Module, result: mutable.ListBuffer[TypeHint]): Unit =
      orderTypeHintsRec(level, module, result, Seq.empty)

    def orderTypeHintsRec(level: Level, module: Module, result: mutable.ListBuffer[TypeHint], stack: Seq[(Module, TypeHint)]): Unit = {
      if (stack.contains((module, self))) { // really need peace
        result += self
        return
      }

      self match {
        case th: ScalarTh =>
          Utils.resolveType(level, module, th) match {
            case (_, _, sd: ScalarDecl) =>
              th.params.foreach(th => th.orderTypeHintsRec(level, module, result, stack :+ (module, self)))
            case (ieSeq, _, sd: StructDecl) =>
              val specMap = Utils.makeSpecMap(sd.params, th.params)
              sd.fields.foreach { f =>
                val specked = f.th.moveToMod(ieSeq).spec(specMap)
                //if (specked != self)
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

    def toDecl(level: Level, module: Module, typeHints: Seq[TypeHint], typeDecls: mutable.ListBuffer[String]): Unit =
      if (self != Builtin.thNil && self != Builtin.thUnreachable) {
        typeDecls +=
          s"""%${self.toString.escaped} = type """ + (self match {
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
                case (_, _, sd: StructDecl) if sd.isBuiltinArray =>
                  val elementTh = th.params(0)
                  sd.getBuiltinArrayLen match {
                    case Some(len) => s"{i64, [$len x ${elementTh.toValue}]}"
                    case None => s"{i64, ${elementTh.toValue}*}"
                  }
                case (ieSeq, _, sd: StructDecl) =>
                  val specMap = Utils.makeSpecMap(sd.params, th.params)
                  val fieldsIr = sd.fields.map { f => f.th.moveToMod(ieSeq).spec(specMap).toValue }
                  if (th.isRefType(level, module))
                    s"%${(self.toString + ".body").escaped}*\n%${(self.toString + ".body").escaped} = type { ${fieldsIr.mkString(", ")} }" // crunch
                  else
                    s"{ ${fieldsIr.mkString(", ")} }"
                case (_, _, ud: UnionDecl) =>
                  val sizes = calculateSizes(typeHints, typeDecls)
                  val max = ud.variants.filter(th => th != Builtin.thNil && th != Builtin.thUnreachable).map(th => sizes(th)).max
                  s"{i64, [$max x i8]}"
              }
            case sth: StructTh =>
              val fieldsIr = sth.seq.map { f => f.typeHint.toValue }
              if (sth.isRefType(level, module))
                s"%${(self.toString + ".body").escaped}*\n%${(self.toString + ".body").escaped} = type { ${fieldsIr.mkString(", ")} }" // crunch
              else
                s"{ ${fieldsIr.mkString(", ")} }"
            case uth: UnionTh =>
              val sizes = calculateSizes(typeHints, typeDecls)
              val max = uth.seq.filter(th => th != Builtin.thNil && th != Builtin.thUnreachable).map(th => sizes(th)).max
              s"{i64, [$max x i8]}"
            case fth: FnTh =>
              s"{ ${fth.ret.toValue} (${(fth.args.map(th => th.toValue) :+ "i8*").mkString(", ")})*, i8* }"
          })
      }
  }

  def calculateSizes(typeHints: Seq[TypeHint], typeDecl: mutable.ListBuffer[String]): Map[TypeHint, Long] = {
    val filtered = typeHints.filter(th => th != Builtin.thNil && th != Builtin.thUnreachable)
    val fw = new FileWriter("/tmp/sizes.ll")

    typeDecl.foreach(td => fw.write(td + "\n"))
    filtered.drop(typeDecl.length).foreach { th =>
      fw.write(s"%${th.toString.escaped} = type opaque\n")
    }

    filtered.take(typeDecl.length).foreach { th =>
      fw.write(s"@${(th + "__Sizeof").escaped} = global %${th.toString.escaped} undef\n")
    }
    fw.close()
    val proc = Runtime.getRuntime.exec(Seq("sh", "-c", "llc-7 -filetype=obj /tmp/sizes.ll -o /tmp/sizes.o && nm -td -n -S /tmp/sizes.o").toArray)
    proc.waitFor()

    val err = new Scanner(proc.getErrorStream)
    while (err.hasNextLine)
      println(err.nextLine())

    val sc = new Scanner(proc.getInputStream)

    filtered.take(typeDecl.length).map { th =>
      val line = sc.nextLine()
      // println(line)
      (th, line.split(" ")(1).toLong)
    }.toMap
  }
}