name := "test-lang-compiler"

version := "0.0.0"

scalaVersion := "2.11.7"


resolvers += Resolver.mavenLocal


libraryDependencies ++= Seq(
  "org.antlr" % "antlr4-runtime" % "4.5.3",
  "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
)