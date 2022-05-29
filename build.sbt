val scala3Version = "3.1.2"

lazy val root = project
  .in(file("."))
  .settings(
    name                             := "pulumi-scala-demo",
    version                          := "0.1.0-SNAPSHOT",
    scalaVersion                     := scala3Version,
    libraryDependencies ++= Seq(
      "com.pulumi" % "aws"    % "(,6.0.0]",
      "com.pulumi" % "pulumi" % "(,1.0]"
    ),
    Compile / run / mainClass        := Some("org.twelvehart.Infrastructure"),
    assembly / assemblyJarName       := "infrastructure.jar",
    assembly / assemblyMergeStrategy := {
      case PathList("com", "google", "rpc", xs @ _*)      => MergeStrategy.first
      case PathList("META-INF", "versions", "9", xs @ _*) => MergeStrategy.first
      case x                                              => MergeStrategy.defaultMergeStrategy(x)
    }
  )
