package es.usj.individualassignment

import androidx.room.*

@Dao
interface GenreDao {
    @Query("SELECT * FROM Genre")
    fun getAll(): List<Genre>

    @Query("SELECT * FROM Genre WHERE id = :id")
    fun getById(id: Int): Genre

    @Update
    fun update(genre: Genre)

    @Insert
    fun insert(genre: Array<Genre>)

    @Delete
    fun delete(genre: Genre)
}