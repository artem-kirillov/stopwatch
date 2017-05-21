import Dependencies._

lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")

lazy val stopwatch = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "stopwatch",
      scalaVersion := "2.12.2",
      version      := "1.0.0-SNAPSHOT"
    )),
    name := "stopwatch",
    libraryDependencies += scalaTest % Test,
    compileScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Compile).toTask("").value,
    (compile in Compile) := ((compile in Compile) dependsOn compileScalastyle).value
  )
