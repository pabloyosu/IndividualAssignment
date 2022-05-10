package es.usj.individualassignment

object SingletonLista {
    val list : ArrayList<Movie> = arrayListOf()
    val listActors : ArrayList<Actor> = arrayListOf();
    val listGenres : ArrayList<Genre> = arrayListOf();

    fun aniadirPeliculas(){
        list.addAll(SingletonMovies.db.MovieDao().getAll())
        listActors.addAll(SingletonMovies.db.ActorDao().getAll())
        listGenres.addAll(SingletonMovies.db.GenreDao().getAll())
    }
}