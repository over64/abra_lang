package m3.typecheck

import m3.parse.Ast0.FnTh

/**
  * Created by over on 20.10.17.
  */
case class DefHeader(pkg: String, name: String, th: FnTh)
case class ModHeader()
