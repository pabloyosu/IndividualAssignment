package es.usj.individualassignment

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Movie::class, Genre::class, Actor::class],
    version = 1
)

@TypeConverters(ArrayListTypeConverter::class)
abstract class MovieDb :RoomDatabase() {
    abstract fun MovieDao(): MovieDao
    abstract fun GenreDao(): GenreDao
    abstract fun ActorDao(): ActorDao
}