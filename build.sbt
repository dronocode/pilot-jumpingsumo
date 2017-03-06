name := "pilot-jumpingsumo"

organization := "com.dronocode"

version := "0.1"

scalaVersion := "2.12.1"

resolvers += Resolver.mavenLocal

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

// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-client
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.7.3"

fork in Test := true

fork in Runtime := true