package ddm.activation

import java.util

import org.osgi.framework.{BundleActivator, BundleContext}
import org.osgi.service.event.{Event, EventAdmin}
import org.osgi.util.tracker.ServiceTracker

class HelloActivator extends BundleActivator {

  override def start(context: BundleContext): Unit = {
    println("HelloActivator start...")

//    val tracker = new ServiceTracker(context, classOf[EventAdmin].getName, null)
//    tracker.open()
//
//    var temp2 = new util.HashMap[String, String]()
//    temp2.put("kkkkey", "vvvvalue")
//
//    val ea: EventAdmin = tracker.getService()
//    if (ea != null) ea.postEvent(new Event("com/test/event", temp2))
//    else println("There is no EventAdmin service.")

  }

  override def stop(context: BundleContext): Unit = {
    println("HelloActivator stop.")
  }

}
