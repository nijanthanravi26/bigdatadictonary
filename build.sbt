name := "bigdatadictonary"

version := "0.1"

scalaVersion := "2.11.11"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.0"
libraryDependencies += "org.apache.kafka" %% "kafka" % "2.1.1"
libraryDependencies += "log4j" % "log4j" % "1.2.17"
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.26"
libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.8.8"