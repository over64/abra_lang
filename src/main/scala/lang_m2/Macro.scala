package lang_m2

import lang_m2.Ast0._

/**
  * Created by over on 23.08.16.
  */
object Macro {
  def genConstructor(td: FactorType): Fn = {
    var nextId = 0
    val th = FnTypeHint(null, td.fields.map { field =>
      FnTypeHintField(null, field.name, field.typeHint)
    }, ScalarTypeHint(null, td.name))

    Fn(null, td.name, Some(th), LlInline(null,
      (td.fields.map { field =>
        nextId += 1

        s"\t%$nextId = getelementptr %struct.${td.name}, %struct.${td.name}* %ret, i32 0, i32 ${nextId - 1}" +
          s"\n\tstore i32 %x, i32* %$nextId"
      } :+ "\tret void").mkString("\n")
    ), Some(ScalarTypeHint(null, td.name)))
  }
}
