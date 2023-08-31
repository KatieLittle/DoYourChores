package dev.smallcat.doyourchores.data.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(ChoreEntity::class)], version = 1, exportSchema = false)
abstract class ChoreRoomDatabase : RoomDatabase() {
    abstract fun choreDao(): ChoreDao
}
