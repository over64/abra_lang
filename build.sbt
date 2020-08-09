import sbt.Keys.{libraryDependencies, version}

scalaVersion := "2.13.1"
resolvers += Resolver.mavenLocal
resolvers += Resolver.sonatypeRepo("public")
concurrentRestrictions in Global += Tags.limit(Tags.Test, 1)

lazy val eva3 = (project in file("v3"))
  .settings(
    name := "eva-lang-compiler-v0.3",
    version := "1.0.0",
    scalaVersion := "2.13.1",
    javacOptions ++= Seq("--enable-preview", "--release", "14"),
    javaOptions += "--enable-preview",
    assemblyJarName in assembly := "eva.jar",
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "4.0.0-RC2",
      "org.antlr" % "antlr4-runtime" % "4.7.2",
      "org.scalatest" % "scalatest_2.13" % "3.0.8" % "test"
    )
  )