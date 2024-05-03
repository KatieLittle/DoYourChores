package dev.smallcat.doyourchores.data.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [(ChoreEntity::class)], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ChoreRoomDatabase : RoomDatabase() {
    abstract fun choreDao(): ChoreDao
}
