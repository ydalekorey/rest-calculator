name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  ws,
  "org.mockito" % "mockito-core" % "1.10.19" % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.webjars" % "jquery" % "3.0.0",
  "org.webjars" % "requirejs" % "2.2.0"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

fork in run := false