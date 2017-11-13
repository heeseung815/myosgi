name := "myosgi"

version := "0.1"

scalaVersion in ThisBuild := Dependencies.Versions.scala

//lazy val myosgi = project.in(file("."))
//  .enablePlugins(SbtOsgi)
//  .settings(
//    name := "myosgi",
//    libraryDependencies ++= Dependencies.ddm,
//    osgiSettings,
//    OsgiKeys.exportPackage := Seq("ddm.osgi.sample.service", "ddm.osgi.sample.activation"),
//    OsgiKeys.bundleActivator := Option("ddm.osgi.sample.activation.Activator")
//  )

lazy val hello_bundle = project.in(file("module/hello_bundle"))
  .enablePlugins(SbtOsgi)
  .settings(
      name := "hello_bundle",
      libraryDependencies ++= Dependencies.hello_bundle,
      osgiSettings,
      OsgiKeys.privatePackage := Seq("ddm.activation"),
      OsgiKeys.bundleActivator := Option("ddm.activation.HelloActivator")
  )

lazy val hellokiller_bundle = project.in(file("module/hellokiller_bundle"))
  .enablePlugins(SbtOsgi)
  .settings(
    name := "hellokiller_bundle",
    libraryDependencies ++= Dependencies.hellokiller_bundle,
    osgiSettings,
    OsgiKeys.privatePackage := Seq("ddm.killer.activation"),
    OsgiKeys.bundleActivator := Option("ddm.killer.activation.HelloKillerActivator")
  )

lazy val movie_bundle = project.in(file("module/movie_bundle"))
  .enablePlugins(SbtOsgi)
  .settings(
    name := "Movie Interface",
    libraryDependencies ++= Dependencies.osgiAll,
    osgiSettings,
    OsgiKeys.bundleSymbolicName := "MovieInterface",
    OsgiKeys.exportPackage := Seq("osgitut.movies")
  )

lazy val moviefinder_bundle = project.in(file("module/moviefinder_bundle"))
  .enablePlugins(SbtOsgi)
  .settings(
    name := "Basic Movie Finder",
    libraryDependencies ++= Dependencies.osgiAll,
    osgiSettings,
    OsgiKeys.bundleSymbolicName := "BasicMovieFinder",
    OsgiKeys.importPackage := Seq("osgitut.movies", "org.osgi.framework.*", "scala.*"),
    OsgiKeys.privatePackage := Seq("osgitut.movies.impl"),
    OsgiKeys.bundleActivator := Option("osgitut.movies.impl.BasicMovieFinderActivator")
  ).dependsOn(movie_bundle)

lazy val extender_bundle = project.in(file("module/extender_bundle"))
  .enablePlugins(SbtOsgi)
  .settings(
    name := "Extender Bundle",
    libraryDependencies ++= Dependencies.osgiAll,
    osgiSettings,
    OsgiKeys.bundleSymbolicName := "ExtenderBundle",
    OsgiKeys.privatePackage := Seq("ddm.extender.activation"),
    OsgiKeys.bundleActivator := Option("ddm.extender.activation.TestExtender")
  )
