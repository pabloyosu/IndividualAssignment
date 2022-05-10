package es.usj.individualassignment

import android.content.Context
import androidx.room.Room

object SingletonMovies {
    lateinit var db: MovieDb
            fun start(context: Context){
                db = Room.databaseBuilder(context, MovieDb::class.java, "movie").build()
            }
}