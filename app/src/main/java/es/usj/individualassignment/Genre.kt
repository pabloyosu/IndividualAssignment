package es.usj.individualassignment

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genre (
    @PrimaryKey
    var id: Int,
    var name: String
)