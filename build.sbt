import SiteKeys._

  libraryDependencies ++= Seq(
    "org.specs2" %% "specs2" % "2.4.5" % "test"
    // with Scala 2.9.2 (specs2 1.12.3 is the latest version for scala 2.9.2)
    // "org.specs2" %% "specs2" % "1.12.3" % "test",
  )

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
                    "releases"  at "http://oss.sonatype.org/content/repositories/releases")

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

name := "scala-totp-auth"

version := "1.1"

scalaVersion := "2.11.2"

scalacOptions in (Compile, doc) ++=
  Opts.doc.sourceUrl("https://github.com/marklister/scala-totp-auth/blob/master/src/main/scala/€{TPL_NAME}.scala")

site.settings

ghpages.settings

git.remoteRepo := "git@github.com:marklister/scala-totp-auth.git"

site.includeScaladoc()

includeFilter in makeSite  ~= { f => f ||    "*.jar" }