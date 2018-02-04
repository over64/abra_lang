import sbt.Keys.{libraryDependencies, version}

resolvers += Resolver.mavenLocal
resolvers += Resolver.sonatypeRepo("public")

lazy val abra1 = (project in file("abra_v1"))
  .settings(
    baseDirectory := file("abra_v1").getAbsoluteFile,
    fork in Test := true,
    name := "abra-lang-compiler_v1",
    version := "1.0.0",
    scalaVersion := "2.11.8",
    assemblyJarName in assembly := "kadabra.jar",
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "3.5.0",
      "org.antlr" % "antlr4-runtime" % "4.7",
      "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test",
      "com.google.code.gson" % "gson" % "2.2.4"
    )
  )


lazy val abra2 = (project in file("abra_v2"))
  .settings(
    name := "abra-lang-compiler_v2",
    version := "1.0.0",
    scalaVersion := "2.11.11",
    assemblyJarName in assembly := "kadabra.jar",
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "3.5.0",
      "org.antlr" % "antlr4-runtime" % "4.7",
      "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
    )
  )

lazy val ide = (project in file("ide"))
  .dependsOn(abra2)
  .settings(
    name := "abra-lang-ide",
    version := "1.0.0",
    scalaVersion := "2.11.8",
    assemblyJarName in assembly := "ide.jar",
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "3.5.0",
      "org.antlr" % "antlr4-runtime" % "4.7",
      "com.sparkjava" % "spark-core" % "2.6.0",
      "org.json4s" % "json4s-native_2.11" % "3.5.3",
      "org.scalatest" % "scalatest_2.11" % "2.2.6" % "test"
    )
  )