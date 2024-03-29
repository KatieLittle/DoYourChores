package dev.smallcat.doyourchores.data.repository.room.di

import android.content.Context
import androidx.room.Room
import dev.smallcat.doyourchores.data.repository.room.ChoreRoomDatabase
import dev.smallcat.doyourchores.data.repository.room.Constants

interface ChoreRoomModule {
    val db : ChoreRoomDatabase
}
class ChoreRoomModuleImpl(context: Context) : ChoreRoomModule {
    override val db: ChoreRoomDatabase by lazy {
        Room.databaseBuilder(
            context,
            ChoreRoomDatabase::class.java,
            Constants.CHORE_TABLE
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}