import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.melvic",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "SimpL",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "org.scala-lang" % "scala-parser-combinators" % "2.11.0-M4"
    )
  )
