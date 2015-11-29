import java.io.{FileReader, File}

/**
  * Created by over on 18.11.15.
  */
object Compiler {

  def main(args: Array[String]) {
    val sourceFile = new File(args(0))
    val ast = ASTGen.genAst(new FileReader(sourceFile))

    println(s"parsed ast:")
    ast.foreach(println(_))

    val pipeline = new Pipeline(sourceFile.getName)
    BitcodeGen.genBitcode(pipeline, ast)
    pipeline.finish()
    println("Done!")
  }
}
