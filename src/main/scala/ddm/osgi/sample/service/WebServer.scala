package ddm.osgi.sample.service

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.concurrent.{ExecutionContextExecutor, Future}

object WebServer {
  def apply(implicit system: ActorSystem) = new WebServer()
}

class WebServer(implicit system: ActorSystem) {

//  implicit val system = ActorSystem("ddm-test-system")
//  implicit val system2 = system
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  private var route: Route = _
  private var bindingFuture: Future[ServerBinding] = _

  def bind: Unit = {
    route =
      path("hello") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
        }
      }

    bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  }

  def terminate: Unit = {
    println("WebServer terminate method called...")

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }
}

object TestClass {
  def apply() = new TestClass()
}
class TestClass {

  def run: Unit ={
    println("TestClass is running...")
  }
}
