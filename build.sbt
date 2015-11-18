name := "test-lang-compiler"

version := "0.0.0"

scalaVersion := "2.11.7"


resolvers += Resolver.mavenLocal


libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)