package ddm.killer.activation

import org.osgi.framework.{Bundle, BundleActivator, BundleContext}

class HelloKillerActivator extends BundleActivator {

  override def start(context: BundleContext): Unit = {
    println("ddm.killer.activation.HelloKillerActivator start...")

    val bundles: Array[Bundle] = context.getBundles
    val hello_bundle = bundles.filter(b => b.getSymbolicName.equals("hello_bundle"))
    hello_bundle(0).uninstall()
    println("\'hello_bundle\', uninstalled.")

    val others = bundles.filter(b => !b.getSymbolicName.equals("hello_bundle"))
    others.foreach(b => println("# " + b.getSymbolicName))
  }

  override def stop(context: BundleContext): Unit = {
    println("ddm.killer.activation.HelloKillerActivator stop.")
  }

}
