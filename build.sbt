name := "pilot-jumpingsumo"

organization := "com.dronocode"

version := "0.1"

scalaVersion := "2.12.1"

resolvers += Resolver.mavenLocal

//libraryDependencies += "com.pragone" % "jpHash" % "1.0-SNAPSHOT"

libraryDependencies += "de.devoxx4kids" % "dronecontroller" % "0.2.0a"

libraryDependencies ++= Seq(
  "org.scalactic" %% "scalactic" % "3.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test")

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.12"  % "test;compile",
  "org.apache.logging.log4j" % "log4j" % "2.5" % "test;compile",
  "org.apache.logging.log4j" % "log4j-api" % "2.5" % "test;compile",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.5" % "test;compile"
)

fork in (Test, run) := true