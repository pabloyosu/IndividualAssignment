package es.usj.individualassignment
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Movie (
    @PrimaryKey
    var id: Int,
    var title: String,
    val genres: List<Int>,
    var description: String,
    var director: String,
    var actors: List<Int>,
    var year: Int,
    var runtime: Int,
    var rating: Float,
    var votes: Int,
    var revenue: Float
):Serializable