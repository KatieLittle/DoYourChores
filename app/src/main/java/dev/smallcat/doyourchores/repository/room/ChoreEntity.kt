package dev.smallcat.doyourchores.repository.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.smallcat.doyourchores.repository.room.Constants.Companion.CHORE_TABLE


@Entity(tableName = CHORE_TABLE)
data class ChoreEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo(name="name")
    var name : String = "",

    @ColumnInfo(name="description")
    var description : String = "",

    @ColumnInfo(name="timeBetweenInDays")
    var timeBetweenInDays : Double = 0.0,
)