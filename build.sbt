ThisBuild / scalaVersion     := "2.13.5"
ThisBuild / organization     := "com.intuit"
ThisBuild / organizationName := "intuit"

version := "1.0"

lazy val root = (project in file("."))
  .settings(
    name := "payments-engine",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.8" % Test,
      "org.mockito" % "mockito-core" % "3.11.2" % Test,
//      "io.prometheus" % "simpleclient" % "0.12.0",
//      "io.prometheus" % "simpleclient_hotspot" % "0.12.0",
//      "io.prometheus" % "simpleclient_httpserver" % "0.12.0",
      "com.github.pureconfig" %% "pureconfig" % "0.11.1",
      "org.apache.kafka" % "kafka-clients" % "2.7.0",
      "com.typesafe.play" %% "play-json" % "2.9.4",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
      "ch.qos.logback" % "logback-classic" % "1.2.10"
    )
  )