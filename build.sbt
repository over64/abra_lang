name := "test-lang-compiler"

version := "0.0.0"

scalaVersion := "2.11.8"


resolvers += Resolver.mavenLocal
resolvers += Resolver.sonatypeRepo("public")

assemblyJarName in assembly := "kadabra.jar"


libraryDependencies ++= Seq(
  "com.github.scopt" %% "scopt" % "3.5.0",
  "org.antlr" % "antlr4-runtime" % "4.5.3",
  "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
)