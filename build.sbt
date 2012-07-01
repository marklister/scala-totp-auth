libraryDependencies ++= Seq("org.specs2" %% "specs2" % "1.11" % "test")

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                    "releases"  at "http://oss.sonatype.org/content/repositories/releases")

name := "scala-totp-auth"

version := "1.0"

scalaVersion := "2.9.2"

//sbtVersion := "0.11.2"

scalacOptions in (Compile, doc) ++=
  Opts.doc.sourceUrl("https://github.com/milo-minderbender/scala-totp-auth/blob/master/src/main/scala/€{TPL_NAME}.scala")
