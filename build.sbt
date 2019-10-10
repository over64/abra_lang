import sbt.Keys.{libraryDependencies, version}

resolvers += Resolver.mavenLocal
resolvers += Resolver.sonatypeRepo("public")
concurrentRestrictions in Global += Tags.limit(Tags.Test, 1)

lazy val eva3 = (project in file("v3"))
  .settings(
    name := "eva-lang-compiler-v0.3",
    version := "1.0.0",
    scalaVersion := "2.12.8",
    assemblyJarName in assembly := "kadabra.jar",
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "3.5.0",
      "org.antlr" % "antlr4-runtime" % "4.7",
      "org.scalatest" % "scalatest_2.12" % "3.0.1" % "test"
    )
  )