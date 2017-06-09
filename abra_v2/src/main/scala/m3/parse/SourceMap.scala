package m3.parse

import scala.collection.mutable

/**
  * Created by over on 25.08.16.
  */
case class AstInfo(fname: String, line: Int, col: Int)

class SourceMap {
  val nodes = new mutable.ListBuffer[(AstInfo, AnyRef)]()

  def add(node: AnyRef, info: AstInfo) =
    nodes += ((info, node))

  def find(node: AnyRef): Option[AstInfo] = {
    nodes.find {
      case (info, _node) => node eq _node
    }.map(_._1)
  }
}