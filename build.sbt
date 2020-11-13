name := "scala.validator"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.0.0-M1"
libraryDependencies ++= {
  Seq(
    "org.scalameta" %% "munit" % "0.7.17" % Test
  )
}
testFrameworks += new TestFramework("munit.Framework")
parallelExecution in Test := false
semanticdbEnabled := true