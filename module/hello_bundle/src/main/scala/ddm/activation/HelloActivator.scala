package ddm.activation

import org.osgi.framework.{BundleActivator, BundleContext}

class HelloActivator extends BundleActivator {

  override def start(context: BundleContext): Unit = {
    println("HelloActivator start...")
  }

  override def stop(context: BundleContext): Unit = {
    println("HelloActivator stop.")
  }

}
