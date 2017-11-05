import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.javadsl.Sink
import akka.stream.{ActorMaterializer, IOResult}
import akka.stream.scaladsl.{FileIO, Keep, RunnableGraph, Source}
import akka.util.ByteString

import scala.concurrent.Future
import scala.language.postfixOps

object TestStreamMain {

  implicit val system = ActorSystem()
  implicit val ec = system.dispatcher
  implicit val mt = ActorMaterializer()

  def main(args: Array[String]): Unit = {
    val source: Source[Int, NotUsed] = Source(1 to 10)
    val sink = Sink.foreach[Int](println(_))

    // connect the Source to the Sink, obtaining a RunnableGraph
    val runnable = source.to(sink)

    // materialize the flow and get the value of the FoldSink
    runnable.run()
  }
}
