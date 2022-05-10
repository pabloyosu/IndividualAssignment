package es.usj.individualassignment

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Actor (
    @PrimaryKey
    var id: Int,
    var name: String
)