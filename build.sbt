import play.sbt.PlayScala._

name := "registry"

scalaVersion in ThisBuild := "2.12.10"

lazy val api = project
  .in(file("api"))
  .enablePlugins(PlayScala)
  .enablePlugins(NewRelic)
  .enablePlugins(JavaAppPackaging, JavaAgent)
  .settings(commonSettings: _*)
  .settings(
    routesImport += "io.flow.registry.v0.Bindables._",
    routesGenerator := InjectedRoutesGenerator,
    javaAgents += "io.kamon" % "kanela-agent" % "1.0.1",
    libraryDependencies ++= Seq(
      ws,
      guice,
      jdbc,
      "io.flow" %% "lib-postgresql-play-play26" % "0.3.48",
      "io.flow" %% "lib-play-graphite-play26" % "0.1.17-k2-15",
      "com.typesafe.play" %% "play-json-joda" % "2.7.4",
      "org.postgresql" % "postgresql" % "42.2.8",
      "net.jcazevedo" %% "moultingyaml" % "0.4.1",
      "io.flow" %% "lib-test-utils" % "0.0.72" % Test,
      "io.flow" %% "lib-usage" % "0.1.8",
      "io.flow" %% "lib-log" % "0.0.87",
      compilerPlugin("com.github.ghik" %% "silencer-plugin" % "1.4.2"),
      "com.github.ghik" %% "silencer-lib" % "1.4.2" % Provided
    ),
    // silence all warnings on autogenerated files
    flowGeneratedFiles ++= Seq(
      "target/.*".r,
      "app/generated/.*".r,
    ),
    // Make sure you only exclude warnings for the project directories, i.e. make builds reproducible
    scalacOptions += s"-P:silencer:sourceRoots=${baseDirectory.value.getCanonicalPath}"
  )

lazy val commonSettings: Seq[Setting[_]] = Seq(
  name ~= ("registry-" + _),
  libraryDependencies ++= Seq(
    specs2 % Test
  ),
  sources in(Compile, doc) := Seq.empty,
  publishArtifact in(Compile, packageDoc) := false,
  resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
  resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
  resolvers += "Artifactory" at "https://flow.jfrog.io/flow/libs-release/",
  credentials += Credentials(
    "Artifactory Realm",
    "flow.jfrog.io",
    System.getenv("ARTIFACTORY_USERNAME"),
    System.getenv("ARTIFACTORY_PASSWORD")
  )
)
version := "0.4.58"
