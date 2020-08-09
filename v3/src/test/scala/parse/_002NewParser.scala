package parse

import java.nio.file.{Files, Paths, StandardOpenOption}

import grammar.EvaLexer.{Emitter, Lex}
import m3.grammar.LiteralCodeGen
import m3.grammar.ParseNS._
import org.scalatest.FunSuite

import scala.collection.immutable.Seq
import scala.collection.mutable.ArrayBuffer

class _002NewParser extends FunSuite {
  sealed trait Expression
  sealed trait FnBody
  sealed trait Level1Declaration
  sealed trait Literal extends Expression {
    val value: String
  }

  case class lNone() extends Literal {
    override val value: String = "none"
  }
  case class lInt(value: String) extends Literal
  case class lFloat(value: String) extends Literal
  case class lBoolean(value: String) extends Literal
  case class lString(value: String) extends Literal
  case class lId(value: String) extends Literal
  case class lNative(code: String) extends FnBody

  sealed trait TypeDecl extends Level1Declaration {
    val name: String
    val params: Seq[GenericTh]
  }

  case class ScalarDecl(ref: Boolean, params: Seq[GenericTh], name: String, llType: String) extends TypeDecl
  case class FieldDecl(isSelf: Boolean, name: String, th: TypeHint)
  case class StructDecl(params: Seq[GenericTh], name: String, fields: Seq[FieldDecl]) extends TypeDecl
  case class UnionDecl(params: Seq[GenericTh], name: String, variants: Seq[TypeHint]) extends TypeDecl

  sealed trait TypeHint
  case class ScalarTh(params: Seq[TypeHint], name: String, ie: Option[String], declaredIn: String /*???*/) extends TypeHint
  case class FieldTh(name: String, typeHint: TypeHint)
  case class StructTh(seq: Seq[FieldTh]) extends TypeHint
  case class UnionTh(seq: Seq[TypeHint]) extends TypeHint
  case class GenericTh(var typeName: String, var isAnon: Boolean = false) extends TypeHint
  case object AnyTh extends TypeHint
  case class FnTh(args: Seq[TypeHint], ret: TypeHint) extends TypeHint

  case class Prop(from: Expression, props: Seq[lId]) extends Expression
  case class Tuple(seq: Seq[Expression]) extends Expression
  case class Cons(sth: ScalarTh, args: Seq[Expression]) extends Expression
  case class SelfCall(fnName: String, self: Expression, args: Seq[Expression]) extends Expression
  case class Call(expr: Expression, args: Seq[Expression]) extends Expression

  case class EvaCode(seq: Seq[Expression]) extends FnBody
  case class Lambda(args: Seq[Arg], body: FnBody) extends Expression

  sealed trait AndOr {
    val left: Expression
    val right: Expression
  }
  case class And(left: Expression, right: Expression) extends Expression with AndOr
  case class Or(left: Expression, right: Expression) extends Expression with AndOr
  case class If(cond: Expression, _do: Seq[Expression], _else: Seq[Expression]) extends Expression
  case class Is(vName: Option[lId], typeRef: TypeHint, _do: Seq[Expression])
  case class Unless(expr: Expression, is: Seq[Is]) extends Expression
  case class While(cond: Expression, _do: Seq[Expression]) extends Expression
  case class Store(th: TypeHint, to: Seq[lId], what: Expression) extends Expression
  case class Ret(what: Option[Expression]) extends Expression
  case object Break extends Expression
  case object Continue extends Expression

  case class Arg(name: String, typeHint: TypeHint)
  case class Def(name: String, lambda: Lambda, retTh: TypeHint) extends Level1Declaration
  case class ImportEntry(modName: String, path: String, withTypes: Seq[String])
  case class Import(seq: Seq[ImportEntry])
  case class Module(pkg: String,
                    imports: Import,
                    lowCode: Seq[lNative],
                    types: Map[String, TypeDecl],
                    defs: Map[String, Def],
                    selfDefs: Map[String, Seq[Def]])


  final class Parser(val ctx: Ctx) extends LiteralCodeGen {
    def rtLitNone() = ctx.tryFetch(Lex.LIT_NONE, { _ => Right(lNone()) })
    def rtLitBool() = ctx.tryFetch(Lex.LIT_BOOL, { _ => Right(lBoolean("true")) })
    def rtLitInt() = ctx.tryFetch(Lex.LIT_INT, { _ => Right(lInt("42")) })
    def rtLitFloat() = ctx.tryFetch(Lex.LIT_FLOAT, { _ => Right(lFloat("3.14")) })
    def rtLitString() = ctx.tryFetch(Lex.LIT_STRING, { _ => Right(lString("")) })

    //FIXME: don't use OR for literals
    def rtLit() = or(rtLitNone, rtLitBool, rtLitInt, rtLitFloat, rtLitString)

    def rIdentIn = {
      ctx.identPush(ctx.identCur + 2); Right(())
    }

    def rIdentOut = {
      ctx.identPop(); Right(())
    }

    def rIdent = l_ident match {
      case Right(token) =>
        val len = Emitter.decodeLen(token)
        if (len != ctx.identCur)
          throw new RuntimeException(s"Expected ident ${ctx.identCur} has $len")
        Right(())
      case left@Left(_) => left
    }

    def rEmptyDelim = Right(())

    def rCommaDelim = for {
      _ <- l_comma
      _ <- opt {rIdent}
    } yield ()

    def rVLineDelim = for {
      _ <- l_v_line
      _ <- opt {rIdent}
    } yield ()

    def rImportEntry = for {
      id <- l_id
      types <- opt {
        for {
          _ <- l_with
          _ <- rIdentIn
          _ <- opt {rIdent}
          seq <- expected {
            many(true, {rEmptyDelim}, {l_type_id})
          }
          _ <- rIdentOut
        } yield seq.map {ctx.tokenText}
      }
    } yield ImportEntry(ctx.tokenText(id), "__", types.getOrElse(Seq()))

    def rImport = for {
      _ <- l_import
      _ <- rIdentIn
      _ <- opt {rIdent}
      ieSeq <- many(true, {rCommaDelim}, {rImportEntry})
      _ <- rIdentOut
    } yield Import(ieSeq)

    def rGenericTh = for {
      id <- l_id
    } yield GenericTh(ctx.tokenText(id))

    def rScalarTh: PResult[ScalarTh] = for {
      mod <- opt {
        for {
          id <- l_id
          _ <- l_dot
        } yield id
      }
      tid <- l_type_id
      params <- opt {
        for {
        _ <- l_l_bracket
        seq <- many(true, {rCommaDelim}, {rTh})
        _ <- l_r_bracket
        } yield seq
      }
    } yield ScalarTh(params.getOrElse(Seq.empty),
      ctx.tokenText(tid), mod.map(t => ctx.tokenText(t)), "__")

    def rFnTh = for {
      _ <- l_l_paren
      argsTh <- many(false, {rCommaDelim}, rTh)
      _ <- l_r_paren
      _ <- l_r_arrow
      retTh <- expected {rTh}
    } yield FnTh(argsTh, retTh)

    def rStructTh = for {
      _ <- l_l_paren
      seq <- many(true, {rCommaDelim}, {
        for {
          id <- l_id
          _ <- l_con
          th <- expected {rTh}
        } yield FieldTh(ctx.tokenText(id), th)
      })
      _ <- l_r_paren
    } yield StructTh(seq)

    def rNonUnionTh: PResult[TypeHint] = or(() => for {
      _ <- l_l_paren
      th <- rUnionTh
      _ <- l_r_paren
    } yield th, () => rScalarTh, () => rGenericTh, () => rFnTh)

    def rUnionTh = for {
      seq <- many(true, rVLineDelim, {rNonUnionTh})
    } yield UnionTh(seq)

    def rTh: PResult[TypeHint] = or(() => rScalarTh, () => rGenericTh, () => rFnTh, () => rUnionTh)
  }

  def parserForInput(input: String) = {
    val source = input.toCharArray
    val tokens = ArrayBuffer[Long]()
    LexUtils.lexRaw(source, { token => tokens += token })
    new Parser(new Ctx(source, tokens.toArray, 1))
  }

  // +- 2, no stack, savepoint for ident
  // rIdent just checks, всегда одинаково
  // type declarations???
  //   1. add metadata to Lex, fix literals
  //   2. fix ident for typehint

  test("parse import") {
    assert(parserForInput(
      """import io, seq,
        |  lib1, lib2,
        |  pg with Connection,
        |  json with
        |    JsonTree JsonPath,
        |  lib3""".stripMargin).rImport ===
      Right(Import(Seq(
        ImportEntry("io", "__", Seq()),
        ImportEntry("seq", "__", Seq()),
        ImportEntry("lib1", "__", Seq()),
        ImportEntry("lib2", "__", Seq()),
        ImportEntry("pg", "__", Seq("Connection")),
        ImportEntry("json","__",Seq("JsonTree", "JsonPath")),
        ImportEntry("lib3", "__", Seq())))))
  }

  test("parse typehint: generic") {
    assert(parserForInput("a").rGenericTh === Right(GenericTh("a")))
  }

  test("parse typehint: scalar") {
    assert(parserForInput("Int").rScalarTh ===
      Right(ScalarTh(Seq.empty, "Int", None, "__")))

    assert(parserForInput("io.File").rScalarTh ===
      Right(ScalarTh(Seq.empty, "File", Some("io"), "__")))

    assert(parserForInput("io.Vec[Int]").rScalarTh ===
      Right(ScalarTh(Seq(ScalarTh(Seq.empty, "Int", None, "__")),
        "Vec", Some("io"), "__")))

    assert(parserForInput("Map[Int, String]").rScalarTh ===
      Right(ScalarTh(
        Seq(
          ScalarTh(Seq.empty, "Int", None, "__"),
          ScalarTh(Seq.empty, "String", None, "__")
        ), "Map", None, "__")))

    assert(parserForInput("Vec[a]").rScalarTh ===
      Right(ScalarTh(Seq(GenericTh("a")), "Vec", None, "__")))
  }

  test("parse typehint: function") {
    assert(parserForInput("() -> a").rFnTh ===
      Right(FnTh(Seq(), GenericTh("a"))))

    assert(parserForInput("(a) -> Int").rFnTh ===
      Right(FnTh(
        Seq(GenericTh("a")),
        ScalarTh(Seq.empty, "Int", None, "__"))))
  }

  test("parse typehint: struct") {
    assert(parserForInput("(a: Int, b: a)").rStructTh ===
      Right(StructTh(Seq(
        FieldTh("a", ScalarTh(Seq.empty, "Int", None, "__")),
        FieldTh("b", GenericTh("a"))))))
  }

  test("parse typehint: union") {
    assert(parserForInput("(Int | a) | a").rUnionTh ===
      Right(UnionTh(Seq(
        UnionTh(Seq(ScalarTh(Seq.empty, "Int", None, "__"), GenericTh("a"))),
        GenericTh("a")))))
  }

  ignore("generate parsers for literals") {
    val valuedLex = Seq(Lex.ID, Lex.TYPE_ID, Lex.IDENT)
    val declarations =
      Lex.values().map { lex =>
        val fnName = "l_" + lex.name().toLowerCase()
        val body = if (valuedLex.contains(lex))
          "{ t => Right(t) }"
        else
          "{ _ => Right(()) }"

        s"@inline def $fnName = ctx.tryFetch(Lex.$lex, $body)"
      }

    val res =
      s"""
         |package m3.grammar
         |
         |import grammar.EvaLexer.Lex
         |import m3.grammar.ParseNS.{PResult, WithCtx}
         |
         |trait LiteralCodeGen extends WithCtx {
         |  ${declarations.mkString("\n\t")}
         |}""".stripMargin

    Files.writeString(Paths.get(System.getProperty("user.dir") +
      "/v3/src/main/scala/m3/grammar/LiteralCodeGen.scala"),
      res, StandardOpenOption.TRUNCATE_EXISTING)
    println(res)
  }
}
