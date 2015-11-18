import java.io.{BufferedReader, File, FileOutputStream, PrintWriter}
import java.util.Scanner

import ASTGen.{LangAST, IVal, InfixCall}

/**
  * Created by over on 18.11.15.
  */
object Compiler {
  def eval(ast: LangAST): Int = {
    ast match {
      case InfixCall(op, l, r) => op match {
        case "*" => eval(l) * eval(r)
        case "/" => eval(l) / eval(r)
        case "+" => eval(l) + eval(r)
        case "-" => eval(l) - eval(r)
      }
      case IVal(i, _) => i
    }
  }

  def main(args: Array[String]) {
    val ast = ASTGen.getAst("(2*2 + 100 * 10 / 4 )*(3 + 2) + 1")
    println(s"parsed ast: $ast")
    println(s"scala eval: ${eval(ast)}")

    val llc = Runtime.getRuntime().exec("llc-3.6")
    val llcIn = new PrintWriter(llc.getOutputStream)
    val llcOut = new Scanner(llc.getInputStream)

    val gcc = Runtime.getRuntime().exec("gcc -x assembler -o bin -")
    val gccIn = new PrintWriter(gcc.getOutputStream)
    val gccErr = new Scanner(gcc.getErrorStream)

    BitcodeGen.genBitcode(llcIn, ast)
    llcIn.flush()
    llcIn.close()

    while (llcOut.hasNextLine)
      gccIn.println(llcOut.nextLine())

    gccIn.close()

    while (gccErr.hasNextLine())
      println(gccErr.nextLine())
  }
}
