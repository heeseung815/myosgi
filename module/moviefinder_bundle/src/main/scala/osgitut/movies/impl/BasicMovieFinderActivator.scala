package osgitut.movies.impl

import org.osgi.framework.{BundleActivator, BundleContext}
import osgitut.movies.MovieFinder

class BasicMovieFinderActivator extends BundleActivator {

  override def start(context: BundleContext): Unit = {
    println("BasicMovieFinderActivator start...")

    val finder = new BasicMovieFinderImpl()
    context.registerService(classOf[MovieFinder].getName, finder, null)
  }

  override def stop(context: BundleContext): Unit = {
    println("BasicMovieFinderActivator stop.")
  }
}
