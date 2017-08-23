package m3.codegen

import java.io.{ByteArrayOutputStream, OutputStream}

import com.sun.beans.TypeResolver
import m3.codegen.Ast1new._

import scala.collection.mutable


/**
  * Created by over on 27.07.17.
  */

case class ClosureRef(specs: Seq[TypeRef], pkg: String, name: String)

case class LineStream(level: Int, chan: ByteArrayOutputStream = new ByteArrayOutputStream()) {
  def line(line: String) = chan.write(("\t" * level + line + "\n").getBytes())
  def deeper = LineStream(level + 1)
  def flushTo(stream: LineStream) = chan.writeTo(stream.chan)
  def flushTo(stream: OutputStream) = chan.writeTo(stream)
}

sealed trait VarType
case object LocalVar extends VarType
case object ParamVar extends VarType
case class CVar(name: String, vtype: VarType, ref: TypeRef)
class Context {
  val vars = mutable.HashMap[String, CVar]()
  def pushVar(v: CVar) = vars.put(v.name, v)
  def findVar(name: String): Option[CVar] = vars.get(name)
}

case class Out(types: LineStream, code: LineStream)

// Типы - файл1
// Код - файл2
object IrGenNew {
  val regTypes = Seq("Int", "Float", "Boolean", "String")
  val builtinTypes = Map(
    "None" -> "void",
    "Boolean" -> "i8",
    "Int" -> "i32",
    "Float" -> "float",
    "String" -> "i8*")

  def isRegisterType(ref: TypeRef): Boolean = ref match {
    case _: FRef => true
    case s: SRef if regTypes.contains(s.name) => true
    case _ => false
  }

  def typeRefToIr(ref: TypeRef): String = ref match {
    case _: FRef => "not implemented"
    case SRef(Seq(), None, name) if builtinTypes.contains(name) => builtinTypes(name)
    case _ => "not implemented"
  }

  // 1 - eval destination
  //   1.1 new var -> ref: None
  //   1.2 declared var -> ref: Some
  // val x = 1
  // var x = 2
  // x = 3
  // x.y.z = S(1, 2)
  def inferIdType(id: Id, ctx: Context): Option[TypeRef] = {
    // FIXME: implement
    ctx.vars.get(id.v).map { v =>
      val finalRef = id.props.foldLeft(v.ref)({
        case (ref, fieldName) => ref
      })
      finalRef
    }
  }
  def evalStoreDest(expectedType: TypeRef, id: Id, ctx: Context, out: Out): String = id match {
    case Id(name, Seq()) => "%" + name
    case Id(name, props) => throw new Exception("not implemented")
  }

  def evalStoreSrc(expectedType: Option[TypeRef], id: Lit, ctx: Context, out: Out): (TypeRef, String) = id match {
    case IConst(value) =>
      expectedType match {
        case None => (SRef.lnspec("Int"), value)
        case _ => throw new Exception("not implemented")
      }
    case Id(name, props) => throw new Exception("not implemented")
  }

  def evalStat(typeResolver: (TypeRef) => Option[(Boolean, Type)],
               closureResolver: (Seq[TypeRef], String, Option[TypeRef], String) => Option[(Boolean, Closure)],
               pkg: String,
               stat: Stat, ctx: Context, out: Out): Unit = stat match {
    case Call(specs, id, args) =>
      ctx.findVar(id.v) match {
        case Some(cvar) => // вызов указателя на функцию, который хранится в локальной переменной
        // может превратиться в SelfCall("get"), если этот объект не является функцией
        case None => // вызов глобальной функции в нашем модуле
          // проверим, что у нас нет доступа к полям, так как у глобальной функции их не может быть
          id.props.head.map { prop => throw new RuntimeException(s"field $prop of ${id.v} not found") }

          closureResolver(specs, pkg, None, id.v)
      }
    case SelfCall(specs, id, self, args) =>
      ctx.findVar(id.v) match {
        case Some(cvar) =>

        case None => // возможно, вызов модуля

      }
    case Store(id, lit) =>
      val defRefOpt = inferIdType(id, ctx)

      // like x.y = z and x is undefined
      if (defRefOpt.isEmpty && !id.props.isEmpty)
        throw new Exception(s"${id.v} is not defined")

      val (srcRef, src) = evalStoreSrc(defRefOpt, lit, ctx, out)
      val srcIrType = typeRefToIr(srcRef)

      if (defRefOpt.isEmpty)
        ctx.pushVar(CVar(id.v, LocalVar, srcRef))

      if (id.v == "$ret" && isRegisterType(srcRef))
        out.code.line(s"ret $srcIrType $src")
      else {
        val dest = evalStoreDest(srcRef, id, ctx, out)
        out.code.line(s"store $src -> $dest")
      }
    case Ret =>
      val retVar = ctx.vars.getOrElse("$ret", throw new RuntimeException("expected ret before!"))
      if (!isRegisterType(retVar.ref)) out.code.line("ret void")
  }


  def evalCode(typeResolver: (TypeRef) => Option[(Boolean, Type)],
               closureResolver: (Seq[TypeRef], String, Option[TypeRef], String) => Option[(Boolean, Closure)],
               pkg: String,
               closure: Closure, out: Out): TypeRef = closure.code match {
    case LLCode(value) =>
      out.code.line(value)
      closure.ret match {
        case None => throw new Exception("Expected type hint for " + closure)
        case Some(ref) => out.code.line(value); ref
      }
    case AbraCode(stats) =>
      val ctx = new Context

      closure.args.foreach(arg => ctx.pushVar(CVar(arg.name, ParamVar, arg.ref)))
      closure.ret.map {
        ref => ctx.pushVar(CVar("$ret", ParamVar, ref))
      }

      stats.foreach(stat => evalStat(typeResolver, closureResolver, pkg, stat, ctx, out))
      SRef.lnspec("Int")
  }

  def evalClosure(typeResolver: (TypeRef) => Option[(Boolean, Type)],
                  closureResolver: (Seq[TypeRef], String, Option[TypeRef], String) => Option[(Boolean, Closure)],
                  pkg: String,
                  closure: Closure, out: Out) = {
    val bodyStream = out.code.deeper
    val retRef = evalCode(typeResolver, closureResolver, pkg, closure, Out(out.types, bodyStream))

    def mapArgs(args: Seq[Field]) = args.map(a => typeRefToIr(a.ref) + " %" + a.name)

    val (ret, args) =
      if (isRegisterType(retRef))
        (typeRefToIr(retRef), mapArgs(closure.args))
      else
        ("void", mapArgs(Field("$ret", retRef) +: closure.args))

    val name = closure.name

    out.code.line(s"define $ret @$name(${
      args.mkString(", ")
    }) {")
    bodyStream.flushTo(out.code)
    out.code.line("}")
  }

  def gen(typeResolver: (TypeRef) => Option[(Boolean, Type)],
          closureResolver: (Seq[TypeRef], String, Option[TypeRef], String) => Option[(Boolean, Closure)],
          pkg: String,
          fn: Closure, out: Out): Unit = {
    evalClosure(typeResolver, closureResolver, pkg, fn, out)
  }
}
