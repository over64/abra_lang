package m3._01parse

import m3.Ast0.{ParseNode, TypeDecl}
import m3.AstInfo

object ParseMeta {
  implicit class ParseNodeMetaImplicit(self: ParseNode) {

    def setLocation(loc: AstInfo): Unit = self.meta.put("source.location", loc)

    def getLocation: Option[AstInfo] = self.meta.get("source.location").map(m => m.asInstanceOf[AstInfo])

    def location: AstInfo = self.getLocation.getOrElse({
      val x = 1 // so weird
      throw new RuntimeException("no location")
    })
  }
}
