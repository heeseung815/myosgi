package osgitut.movies.impl

import osgitut.movies.{Movie, MovieFinder}

class BasicMovieFinderImpl extends MovieFinder {

  private val movies = Array(new Movie("Solution", "UANGEL"), new Movie("IoT", "UANGEL"))

  override def findAll: Array[Movie] = {
    println("findAll called...")
    movies
  }

}
