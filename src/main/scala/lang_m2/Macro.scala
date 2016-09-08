package lang_m2

import lang_m2.Ast0._

/**
  * Created by over on 23.08.16.
  */
object Macro {
  def genConstructor(scope: Scope, td: FactorType): Fn = {
    var nextId = 0
    val th = FnTypeHint(td.fields.map { field =>
      FnTypeHintField(field.name, field.typeHint)
    }, ScalarTypeHint(td.name))

    Fn(td.name, Some(th), LlInline(
      (td.fields.map { field =>
        nextId += 1
        val lowTh = scope.toLow(field.typeHint).name

        s"\t%$nextId = getelementptr %struct.${td.name}, %struct.${td.name}* %ret, i32 0, i32 ${nextId - 1}\n" +
          s"\tstore $lowTh %${field.name}, $lowTh* %$nextId"
      } :+ "\tret void").mkString("\n")
    ), None)
  }

  def genEquals(scope: Scope, td: FactorType): Fn = {
    val th = FnTypeHint(Seq(FnTypeHintField("self", ScalarTypeHint(td.name)), FnTypeHintField("other", ScalarTypeHint(td.name))),
      ScalarTypeHint("Boolean"))
    val lowTh = scope.toLow(ScalarTypeHint(td.name)).name

    Fn("==", Some(th), LlInline(
      s"""
         |    %1 = getelementptr $lowTh, $lowTh* null, i32 1
         |    %2 = ptrtoint $lowTh* %1 to i64
         |    %3 = bitcast $lowTh* %self to i8*
         |    %4 = bitcast $lowTh* %other to i8*
         |    %5 = call i32 @memcmp(i8* %3, i8* %4, i64 %2)
         |    %6 = icmp eq i32 %5, 0
         |    ret i1 %6
      """.stripMargin
    ), None)
  }
  def genNotEquals(scope: Scope, td: FactorType): Fn = {
    val thBool = Some(ScalarTypeHint("Boolean"))
    val thSelf = Some(ScalarTypeHint(td.name))

    Fn("!=", None, Block(Seq(FnArg("self", thSelf), FnArg("other", thSelf)), Seq(
      Call("!", Tuple(Seq(Call("==", Tuple(Seq(lId("self"), lId("other")))))))
    )), thBool)
  }
}
