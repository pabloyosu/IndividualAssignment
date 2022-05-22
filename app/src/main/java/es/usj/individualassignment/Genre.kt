package es.usj.individualassignment

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Genre (
    @PrimaryKey
    var id: Int,
    var name: String
):Serializable