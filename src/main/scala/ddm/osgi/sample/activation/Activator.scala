package ddm.osgi.sample.activation

import akka.actor.{ActorSystem, Props}
import akka.osgi.ActorSystemActivator
import ddm.osgi.sample.service.{SomeActor, WebServer}
import org.osgi.framework.BundleContext


class Activator extends ActorSystemActivator {

  private var server: WebServer = _

  override def configure(context: BundleContext, system: ActorSystem): Unit = {
//    registerService(context, system)

//    val actorSystemName = getActorSystemName(context)
//    println(s"ActorSystemName = $actorSystemName, ${system.name}")
    println(s"ActorSystemName = ${system.name}")
    server = WebServer(system)
    server.bind


//    val someActor = system.actorOf(Props[SomeActor], "ddm-actor")
//    someActor ! "test"
  }

  override def stop(context: BundleContext): Unit = {
    super.stop(context)
    server.terminate
  }
}

/*
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import ddm.osgi.sample.service.WebServer
import org.osgi.framework.{BundleActivator, BundleContext}

class Activator extends BundleActivator {
  println("MyOsgi Activator created")

  private var server: WebServer = _

  override def start(context: BundleContext): Unit = {
    println("MyOsgi Activator start method called...")

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val route =
      path("hello") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

//    TestClass().run

//    server = WebServer()

//    context.registerService(classOf[WebServer].getName, server, null)
//    server = WebServer(context)
//    server.bind
  }

  override def stop(context: BundleContext): Unit = {
    println("MyOsgi Activator stop method called...")

    server.terminate
  }

}
*/