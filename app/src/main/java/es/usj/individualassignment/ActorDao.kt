package es.usj.individualassignment

import androidx.room.*

@Dao
interface ActorDao {
    @Query("SELECT * FROM Actor")
    fun getAll(): List<Actor>

    @Query("SELECT * FROM Actor WHERE id = :id")
    fun getById(id: Int): Actor

    @Update
    fun update(actor: Actor)

    @Insert
    fun insert(actor: Array<Actor>)

    @Delete
    fun delete(actor: Actor)
}