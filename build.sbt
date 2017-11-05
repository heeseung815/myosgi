name := "myosgi"

version := "0.1"

scalaVersion in ThisBuild := Dependencies.Versions.scala

lazy val myosgi = project.in(file("."))
  .enablePlugins(SbtOsgi)
  .settings(
    name := "myosgi",
    libraryDependencies ++= Dependencies.ddm,
    osgiSettings,
    OsgiKeys.exportPackage := Seq("ddm.osgi.sample.service", "ddm.osgi.sample.activation"),
    OsgiKeys.bundleActivator := Option("ddm.osgi.sample.activation.Activator")
  )
