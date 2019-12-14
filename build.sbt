
lazy val root = (project in file("."))
  .enablePlugins(SbtTwirl, GitVersioning, GitBranchPrompt)
  .settings(
    organization := "io.buddho.akka",
    name := "akka-http-twirl",
    git.baseVersion := "1.0",
    scalaVersion := "2.12.1",
    crossScalaVersions := Seq("2.11.8", "2.12.1", "2.13.1"),
    scalacOptions := Seq("-unchecked", "-deprecation", "-feature"),
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint", "-encoding", "UTF-8"),
    bintrayOrganization := Some("buddho"),
    bintrayRepository := "mvn-public",
    publishMavenStyle := true,
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "twirl-api" % "1.4.2",
      "com.typesafe.akka" %% "akka-http" % "10.1.8",
      "com.typesafe.akka" %% "akka-stream" % "2.5.26" % Provided,
      "org.scalatest"     %% "scalatest" % "3.1.0" % Test
    )
  )


