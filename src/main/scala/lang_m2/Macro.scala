package lang_m2

import lang_m2.Ast0._

/**
  * Created by over on 23.08.16.
  */
object Macro {
  def genConstructor(td: FactorType): Fn = {
    var nextId = 0
    val th = FnTypeHint(td.fields.map { field =>
      FnTypeHintField(field.name, field.typeHint)
    }, ScalarTypeHint(td.name))

    Fn(td.name, Some(th), LlInline(
      (td.fields.map { field =>
        nextId += 1

        s"\t%$nextId = getelementptr %struct.${td.name}, %struct.${td.name}* %ret, i32 0, i32 ${nextId - 1}" +
          s"\n\tstore i32 %x, i32* %$nextId"
      } :+ "\tret void").mkString("\n")
    ), Some(ScalarTypeHint(td.name)))
  }
}
