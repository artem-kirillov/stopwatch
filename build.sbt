import Dependencies._

lazy val stopwatch = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "stopwatch",
      scalaVersion := "2.12.2",
      version      := "1.0.0-SNAPSHOT"
    )),
    name := "stopwatch",
    libraryDependencies += scalaTest % Test
  )
