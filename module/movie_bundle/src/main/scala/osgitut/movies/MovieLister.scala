package osgitut.movies

trait MovieLister {
  def listByDirector(name: String): Array[Movie]
}