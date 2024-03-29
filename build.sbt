
name := "Splat"

version := "2.0.0"

scalaVersion := "2.9.1"

// set the main Scala source directory to be <base>/src
//scalaSource in Compile <<= baseDirectory(_ / "src")

// set the Scala test source directory to be <base>/test
//scalaSource in Test <<= baseDirectory(_ / "test")
resolvers += "Scala-Tools Maven2 Snapshots Repository" at "http://oss.sonatype.org/content/repositories/releases"

// add a test dependencies
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.9" % "test"

libraryDependencies += "org.specs2" %% "specs2" % "1.9" % "test"

//Add libs
//knock off to gen markdown to XHTML
libraryDependencies += "com.tristanhunt" % "knockoff_2.9.1" % "0.8.0-16"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "6.0.4"

libraryDependencies += "org.scalaz" %% "scalaz-scalacheck-binding" % "6.0.4"

// don't buffer output from tests until they finish (which is the default)
logBuffered in Test := false

// reduce the maximum number of errors shown by the Scala compiler
maxErrors := 20

// increase the time between polling for file changes when using continuous execution
pollInterval := 1000

// append several options to the list of options passed to the Java compiler
javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

// allow scalac plugins
autoCompilerPlugins := true

// add scalac plugins for sxr (scala xray) to doco the source code
addCompilerPlugin("org.scala-tools.sxr" % "sxr_2.9.0" % "0.2.7")

scalacOptions <+= scalaSource in Compile map { "-P:sxr:base-directory:" + _.getAbsolutePath }

//scalaVersion := "2.9.1"

// append -deprecation to the options passed to the Scala compiler
scalacOptions += "-deprecation"

// define the statements initially evaluated when entering 'console', 'console-quick', or 'console-project'
initialCommands := """
    import System.{currentTimeMillis => now}
    def time[T](f: => T): T = {
        val start = now
        try { f }
        finally { println("Elapsed: " + (now - start)/1000.0 + " s") }
    }
"""

// set the initial commands when entering 'console' only
//initialCommands in console := "import myproject._"

// set the main class for packaging the main jar
// 'run' will still auto-detect and prompt
// change Compile to Test to set it for the test jar
//mainClass in (Compile, packageBin) := Some("myproject.com.owtelse.MyMain")

// set the main class for the main 'run' task
// change Compile to Test to set it for 'test:run'
mainClass in (Compile, run) := Some("com.owtelse.MyMain")

// add <base>/input to the files that '~' triggers on
watchSources <+= baseDirectory map { _ / "input" }

// add a maven-style repository
//resolvers += "name" at "url"

// add a sequence of maven-style repositories
//resolvers ++= Seq("name" at "url")

// define the repository to publish to
//publishTo := Some("name" at "url")

// set Ivy logging to be at the highest level
ivyLoggingLevel := UpdateLogging.Full

// disable updating dynamic revisions (including -SNAPSHOT versions)
offline := true

// set the prompt (for this build) to include the project id.
shellPrompt in ThisBuild := { state => Project.extract(state).currentRef.project + "> " }

// set the prompt (for the current project) to include the username
shellPrompt := { state => System.getProperty("user.name") + "> " }

// disable printing timing information, but still print [success]
showTiming := false

// disable printing a message indicating the success or failure of running a task
showSuccess := false

// change the format used for printing task completion time
timingFormat := {
    import java.text.DateFormat
    DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT)
}

// disable using the Scala version in output paths and artifacts
crossPaths := false

// fork a new JVM for 'run' and 'test:run'
fork := true

// fork a new JVM for 'test:run', but not 'run'
fork in Test := true

// add a JVM option to use when forking a JVM for 'run'
javaOptions += "-Xmx2G"

// only use a single thread for building
parallelExecution := false

// Execute tests in the current project serially
//   Tests from other projects may still run concurrently.
parallelExecution in Test := false

// set the location of the JDK to use for compiling Java code.
// if 'fork' is true, this is used for 'run' as well
//javaHome := Some(file("/usr/lib/jvm/sun-jdk-1.6"))

// Use Scala from a directory on the filesystem instead of retrieving from a repository
//scalaHome := Some(file("/home/user/scala/trunk/"))

// don't aggregate clean (See FullConfiguration for aggregation details)
aggregate in clean := false

// only show warnings and errors on the screen for compilations.
//  this applies to both test:compile and compile and is Info by default
logLevel in compile := Level.Warn

// only show warnings and errors on the screen for all tasks (the default is Info)
//  individual tasks can then be more verbose using the previous setting
logLevel := Level.Info

// only store messages at info and above (the default is Debug)
//   this is the logging level for replaying logging with 'last'
persistLogLevel := Level.Debug

// only show 20 lines of stack traces
traceLevel := 20

// only show stack traces up to the first sbt stack frame
traceLevel := 0

// add SWT to the unmanaged classpath
//unmanagedJars in Compile += Attributed.blank(file("/usr/share/java/swt.jar"))

// publish test jar, sources, and docs
publishArtifact in Test := true

// disable publishing of main docs
//publishArtifact in (Compile, packageDoc) := false

// change the classifier for the docs artifact
//artifactClassifier in packageDoc := Some("doc")

// Copy all managed dependencies to <build-root>/lib_managed/
//   This is essentially a project-local cache and is different
//   from the lib_managed/ in sbt 0.7.x.  There is only one
//   lib_managed/ in the build root (not per-project).
//retrieveManaged := true

// Specify a file containing credentials for publishing. The format is:
//realm=Sonatype Nexus Repository Manager
//host=nexus.scala-tools.org
//user=admin
//password=admin123
//
// credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

// Directly specify credentials for publishing.
// credentials += Credentials("Sonatype Nexus Repository Manager", "nexus.scala-tools.org", "admin", "admin123")

