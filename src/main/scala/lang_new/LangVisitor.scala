package lang_new

import grammar.HelloParser._
import grammar.HelloVisitor
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor
import scala.collection.JavaConversions._

class LangVisitor extends AbstractParseTreeVisitor[AST0] with HelloVisitor[AST0] {
  var fnBodySeq = 0

  def nextFnBodyId = {
    fnBodySeq += 1
    fnBodySeq
  }

  var langDefSeq = 0

  def nextLangDef = {
    langDefSeq += 1
    langDefSeq
  }

  override def visitValDef(ctx: ValDefContext): AST0 = {
    val vi = VarIdent(nextFnBodyId, ctx.name.getText)
    val ti = TypeIdent(ctx.ttype.getText)
    val init = ctx.init.accept(this).asInstanceOf[InitExpression]
    ValDef(nextFnBodyId, vi, ti, init)
  }

  override def visitVarDef(ctx: VarDefContext): AST0 = {
    val vi = VarIdent(nextFnBodyId, ctx.name.getText)
    val ti = TypeIdent(ctx.ttype.getText)
    val init = ctx.init.accept(this).asInstanceOf[InitExpression]
    VarDef(nextFnBodyId, vi, ti, init)
  }

  override def visitStructParamDef(ctx: StructParamDefContext): AST0 =
    Param(VarIdent(nextFnBodyId, ctx.name.getText), TypeIdent(ctx.ttype.getText))

  override def visitAbraTypeDef(ctx: AbraTypeDefContext): AST0 = {
    val first = ctx.first.accept(this).asInstanceOf[Param]
    val all = Seq(first) ++ ctx.rest.iterator().map { param =>
      param.accept(this).asInstanceOf[Param]
    }.toSeq

    AbraTypeDef(nextLangDef, TypeIdent(ctx.name.getText), all)
  }

  override def visitLlvmTypeDef(ctx: LlvmTypeDefContext): AST0 =
    LlvmTypeDef(nextLangDef, TypeIdent(ctx.name.getText), ctx.body.map { t => t.getText }.filter { t => !t.trim.isEmpty }.mkString(""))


  override def visitAtomExp(ctx: AtomExpContext): AST0 =
    Uint(nextFnBodyId, ctx.UINT().getText.toInt)

  override def visitVarIdentExp(ctx: VarIdentExpContext): AST0 =
    VarIdent(nextFnBodyId, ctx.VAR_IDENT().getText)

  override def visitAccess(ctx: AccessContext): AST0 = {
    val id1 = Seq(ctx.id1.getType match {
      case VAR_IDENT => VarIdent(nextFnBodyId, ctx.id1.getText)
      case UINT => Uint(nextFnBodyId, ctx.id1.getText.toInt)
      case _ => FnIdent(ctx.id1.getText)
    })
    val tail = ctx.accList.map { acc =>
      acc.getType match {
        case VAR_IDENT => VarIdent(nextFnBodyId, acc.getText)
        case _ => FnIdent(acc.getText)
      }
    }
    Access(nextFnBodyId, id1 ++ tail)
  }

  override def visitDirectCallExp(ctx: DirectCallExpContext): AST0 = {
    val calleeAndFn = ctx.acc.accept(this).asInstanceOf[Access]
    val fn = calleeAndFn.seq.last match {
      case VarIdent(id, name) => FnIdent(name)
      case f: FnIdent => f
    }
    val callee = calleeAndFn.seq.reverse.drop(1).reverse

    if (ctx.first == null) FnCall(nextFnBodyId, fn, if (callee.isEmpty) Seq() else Seq(Access(nextFnBodyId, callee)))
    else {
      val first = ctx.first.accept(this).asInstanceOf[CallArg]
      val args = Seq(first) ++ ctx.rest.iterator().map { arg =>
        arg.accept(this).asInstanceOf[CallArg]
      }.toSeq

      FnCall(nextFnBodyId, fn, (if (callee.isEmpty) Seq() else Seq(Access(nextFnBodyId, callee))) ++ args)
    }
  }

  override def visitParenExpCall(ctx: ParenExpCallContext): AST0 = {
    //'(' cont=expr ')' ('.' accList+=(VAR_IDENT | '+' | '-' | '*' | '/' | '::' | '=='))+ ('(' (first=expr (',' rest+=expr)* )? ')')? #parenExpCall
    // (1 + 1).x.+(1)
    // (1 + 1).+(1)

    val cont = ctx.cont.accept(this)
    val accList = ctx.accList.reverse.drop(1).reverse.map { acc =>
      acc.getType match {
        case VAR_IDENT => VarIdent(nextFnBodyId, acc.getText)
        case _ => Uint(nextFnBodyId, acc.getText.toInt)
      }
    }

    val fn = FnIdent(ctx.accList.last.getText)
    val directArgs =
      if (ctx.first == null) Seq()
      else {
        val first = ctx.first.accept(this).asInstanceOf[CallArg]
        Seq(first) ++ ctx.rest.iterator().map { arg =>
          arg.accept(this).asInstanceOf[CallArg]
        }.toSeq
      }

    FnCall(nextFnBodyId, fn, Seq(Access(nextFnBodyId, Seq(cont) ++ accList)) ++ directArgs)
  }

  override def visitSimpleParen(ctx: SimpleParenContext): AST0 =
    ctx.cont.accept(this)

  override def visitAccessExp(ctx: AccessExpContext): AST0 =
    ctx.access().accept(this)

  override def visitPriority1Expr(ctx: Priority1ExprContext): AST0 =
    FnCall(nextFnBodyId, FnIdent(ctx.op.getText), Seq(ctx.left.accept(this).asInstanceOf[CallArg], ctx.right.accept(this).asInstanceOf[CallArg]))

  override def visitPriority2Expr(ctx: Priority2ExprContext): AST0 =
    FnCall(nextFnBodyId, FnIdent(ctx.op.getText), Seq(ctx.left.accept(this).asInstanceOf[CallArg], ctx.right.accept(this).asInstanceOf[CallArg]))

  override def visitPriority3Expr(ctx: Priority3ExprContext): AST0 =
    FnCall(nextFnBodyId, FnIdent(ctx.op.getText), Seq(ctx.left.accept(this).asInstanceOf[CallArg], ctx.right.accept(this).asInstanceOf[CallArg]))

  override def visitFnArg(ctx: FnArgContext): AST0 =
    Param(VarIdent(nextFnBodyId, ctx.name.getText), TypeIdent(ctx.ttype.getText))


  override def visitFnArgs(ctx: FnArgsContext): AST0 = {
    val first = ctx.first.accept(this).asInstanceOf[Param]
    val rest = ctx.rest.map { p =>
      p.accept(this).asInstanceOf[Param]
    }
    Params(Seq(first) ++ rest)
  }

  override def visitLlvmFnDef(ctx: LlvmFnDefContext): AST0 = {
    val ret = if (ctx.ret != null) Some(TypeIdent(ctx.ret.getText)) else None

    val params = if (ctx.args == null)
      Seq()
    else
      visitFnArgs(ctx.args).asInstanceOf[Params].seq

    LlvmFn(nextLangDef, FnIdent(ctx.name.getText), params,
      ctx.body.map { t =>
        t.getText + " "
      }.mkString("").replace("% ", "%").replace(" . ", ".")
        .replace(" ,", ",").replace(" *", "*").replace("@ ", "@")
        //ppc
        .replace("c \" %d \\ 0 A \\ 00 \"", "c\"%d\\0A\\00\""), ret)

  }

  override def visitFnBodyDef(ctx: FnBodyDefContext): AST0 = {
    val defs = (0 to ctx.getChildCount).map { i => ctx.getChild(i) }
      .filter(_ != null)
      .map { c =>
        println(s"child $c")
        visit(c).asInstanceOf[FnBody]
      }.filter(_ != null)

    AbraFnBody(defs)
  }

  override def visitAbraFnDef(ctx: AbraFnDefContext): AST0 = {
    val ret = if (ctx.ret != null) Some(TypeIdent(ctx.ret.getText)) else None

    val params = if (ctx.args == null)
      Seq()
    else
      visitFnArgs(ctx.args).asInstanceOf[Params].seq
    val body = if (ctx.body == null)
      Seq()
    else
      visitFnBodyDef(ctx.body).asInstanceOf[AbraFnBody].body

    AbraFn(nextLangDef, FnIdent(ctx.name.getText), params, body, ret)
  }

  override def visitFnDef(ctx: FnDefContext): AST0 =
    if (ctx.abra != null)
      visitAbraFnDef(ctx.abra)
    else
      visitLlvmFnDef(ctx.llvm)


  override def visitTypeDef(ctx: TypeDefContext): AST0 =
    if (ctx.abra != null)
      visitAbraTypeDef(ctx.abra)
    else
      visitLlvmTypeDef(ctx.llvm)

  override def visitLang(ctx: LangContext): AST0 = {
    val all = ctx.types.map {
      t => t.accept(this).asInstanceOf[Lang]
    } ++
      ctx.functions.map {
        t => t.accept(this).asInstanceOf[Lang]
      }

    Module(all.sortBy(_.id).toSeq)
  }
}
