package dev.smallcat.doyourchores.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.smallcat.doyourchores.domain.Chore

@Database(entities = [(ChoreEntity::class)], version = 1, exportSchema = false)
abstract class ChoreRoomDatabase : RoomDatabase() {
    abstract fun choreDao(): ChoreDao
}
