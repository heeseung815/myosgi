package ddm.extender.activation

import java.io.{File, FileWriter}
import java.nio.file.{Files, Paths, StandardOpenOption}
import java.text.SimpleDateFormat
import java.util
import java.util.Date

import org.osgi.framework.{BundleActivator, BundleContext, BundleEvent, BundleListener}
import org.osgi.service.event.{Event, EventConstants, EventHandler}


class TestExtender extends BundleActivator with EventHandler {

  val topics: Array[String] = Array("com/test/event", "org/osgi/framework/BundleEvent/*")

  override def start(context: BundleContext): Unit = {
    println("ddm.extender.activation.TestExtender start...")

//    context.addBundleListener(this)

    val temp = new util.Hashtable[String, Any]()
    temp.put(EventConstants.EVENT_TOPIC, topics)

    context.registerService(classOf[EventHandler].getName, this, temp)
  }

  override def stop(context: BundleContext): Unit = {
    println("ddm.extender.activation.TestExtender stop.")

//    context.removeBundleListener(this)
  }

//  override def bundleChanged(event: BundleEvent): Unit = {
//    println("ddm.extender.activation.TestExtender bundleChanged called...")
//
//    println(s"Bundle [${event.getBundle.getSymbolicName}] ::: ${getStateName(event.getType)}")
//  }

  private def getStateName(state: Int): String = {
    val stateName = state match {
      case BundleEvent.INSTALLED => "INSTALLED"
      case BundleEvent.STARTED => "STARTED"
      case BundleEvent.STOPPED => "STOPPED"
      case BundleEvent.UPDATED => "UPDATED"
      case BundleEvent.UNINSTALLED => "UNINSTALLED"
      case BundleEvent.RESOLVED => "RESOLVED"
      case BundleEvent.UNRESOLVED => "UNRESOLVED"
      case BundleEvent.STARTING => "STARTING"
      case BundleEvent.STOPPING => "STOPPING"
      case _ => s"??? ${state.hashCode().hashCode()}"
    }
    stateName
  }

  override def handleEvent(event: Event): Unit = {
    val names = "timestamp, bundle,x,y,z, event.topics".replaceAll(" ", "").split(",")

//    println(s"[Event Received] Topic: ${event.getTopic}")
//    event.getPropertyNames.foreach(ep => println(s"[Property] ${ep} :: ${event.getProperty(ep)}"))

//    event.getPropertyNames.foreach {
//      case propertyName: String =>
//        if (propertyName.equals("timestamp")) {
//          val property = event.getProperty(propertyName)
//          property.asInstanceOf[Long] match {
//            case x: Long =>
//              val fmt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,SSS")
//              val timeString = fmt.format(new Date(x))
//              println(s"#### $timeString")
//            case _ => println("### Any")
//          }
//
//        } else {
//          println(s"[Property] $propertyName :: ${event.getProperty(propertyName)}")
//        }
//      case _ => ???
//    }
    var result = ""
//    event.getPropertyNames.filter(names.contains(_)).foreach {
//      name =>
//        val prop = event.getProperty(name)
//        if (name.equals("timestamp")) {
//          result += new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,SSS").format(new Date(prop.asInstanceOf[Long])) + " "
//        } else {
//          result += prop + " "
//        }
//
//    }

    names.filter(event.getPropertyNames.contains(_)).foreach { name =>
      val prop = event.getProperty(name)
      if (name.equals("timestamp")) {
        result += new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,SSS").format(new Date(prop.asInstanceOf[Long])) + " "
      } else {
        result += prop + " "
      }
    }


    result = result.trim + "\n"
    // 로컬 특정 경로에 .txt 파일로 이벤트 내용을 기록한다.
//    val fw = new FileWriter("/Users/hscho/osgi_event.log", true)
//    try {
//      fw.write(result.trim + "\n")
//    } finally {
//      fw.close()
//    }
    val fileName = "osgi_events.log"
    val uriPath = Paths.get("/Users/hscho/")
    val writePath = Paths.get(uriPath.toString, fileName)

    Files.write(writePath, result.getBytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND)

  }

}
