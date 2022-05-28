val scala3Version = "3.1.2"

lazy val root = project
  .in(file("."))
  .settings(
    name                             := "quickstart",
    version                          := "0.1.0-SNAPSHOT",
    scalaVersion                     := scala3Version,
    libraryDependencies ++= Seq(
      "com.pulumi"     % "aws"        % "(,6.0.0]",
      "com.pulumi"     % "pulumi"     % "(,1.0]",
      "com.pulumi"     % "aws-native" % "(,1.0]",
      "org.scalameta" %% "munit"      % "0.7.29" % Test
    ),
    Compile / run / mainClass        := Some("com.twelvehart.Infrastructure"),
    assembly / assemblyJarName       := "infrastructure.jar",
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case _                             => MergeStrategy.first
    }
  )
