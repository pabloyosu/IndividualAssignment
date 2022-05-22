package es.usj.individualassignment

import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM Movie WHERE id = :id")
    fun getById(id: Int): Movie

    @Update
    fun update(movie: Movie)

    @Insert
    fun insert(movie: Array<Movie>)

    @Delete
    fun delete(movie: Movie)

    @Insert
    fun insert(movie: Movie)

    @Query("SELECT MAX (id) FROM Movie ")
    fun getMax(): Int
}