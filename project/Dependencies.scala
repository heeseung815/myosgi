import sbt._

object Dependencies {
  object Versions {
    val scala = "2.12.3"
    val akka = "2.5.3"
//    val phantom = "2.11.2"
//    val akka_cassandra = "0.54"
  }

  val akka_http = "com.typesafe.akka" %% "akka-http" % "10.0.10"
  val akka_stream = "com.typesafe.akka" %% "akka-stream" % Versions.akka
  val akka_actor = "com.typesafe.akka" %% "akka-actor" % Versions.akka force()
  val akka_osgi = "com.typesafe.akka" %% "akka-osgi" % Versions.akka  exclude("org.osgi.core", "org.osgi.compendium") changing()
  val akka_remote = "com.typesafe.akka" %% "akka-remote" % Versions.akka              changing()
  val akka_cluster = "com.typesafe.akka" %% "akka-cluster" % Versions.akka force()
  val config = "com.typesafe" % "config" % "1.3.1"

  val osgiCore = "org.osgi" % "org.osgi.core" % "4.3.0" % "provided"
  val osgiCompendium = "org.osgi" % "org.osgi.compendium" % "4.3.0"
  val osgiServiceCM = "org.osgi" % "org.osgi.service.cm" % "1.5.0"

//  val manager = Seq(osgiCore, osgiCompendium)
//  val core = Seq(akka_actor, akka_osgi, akka_remote, akka_cluster, config, osgiCore, osgiCompendium)
//  val command = Seq(akka_actor, osgiCore, osgiCompendium)
//  val configuration_test = Seq(osgiCore, osgiCompendium, osgiServiceCM)
//  val configuration_factory_test = Seq(osgiCore, osgiCompendium, osgiServiceCM)

  val ddm = Seq(akka_http, akka_stream, akka_actor, akka_osgi, akka_remote, osgiCore, osgiCompendium)
  val hello_bundle = Seq(osgiCore, osgiCompendium)
}