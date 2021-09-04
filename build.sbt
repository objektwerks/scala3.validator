name := "scala3.validator"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "3.0.2"
libraryDependencies ++= {
  Seq(
    "com.lihaoyi" % "ujson_2.13" % "1.4.0" % Test,
    "org.scalatest" %% "scalatest" % "3.2.9" % Test
  )
}