organization := "io.buddho"

name := "akka-http-twirl"

scalaVersion := "2.11.7"

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

lazy val root = (project in file("."))
  .enablePlugins(SbtTwirl, GitVersioning, GitBranchPrompt)
  .settings(
    name := "akka-http-twirl",
    git.baseVersion := "0.1.0",
    scalaVersion := "2.11.7",
    scalacOptions := Seq("-unchecked", "-deprecation", "-feature"),
    bintrayOrganization := Some("buddho"),
    bintrayRepository := "mvn-public",
    publishMavenStyle := false,
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http-experimental" % "1.0",
      "com.typesafe.play" %% "twirl-api"              % "1.1.1",
      "org.scalatest"     %% "scalatest"              % "2.2.5" % "test"
    )
  )


