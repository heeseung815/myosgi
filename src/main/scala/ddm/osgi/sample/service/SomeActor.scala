package ddm.osgi.sample.service

import akka.actor.Actor

class SomeActor extends Actor {

  override def receive: Receive = {
    case _ => println("received msg ......")
  }
}
