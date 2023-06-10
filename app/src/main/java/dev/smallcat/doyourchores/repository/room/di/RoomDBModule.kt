package dev.smallcat.doyourchores.repository.room.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.smallcat.doyourchores.repository.iChoreRepository
import dev.smallcat.doyourchores.repository.room.ChoreDao
import dev.smallcat.doyourchores.repository.room.ChoreEntity
import dev.smallcat.doyourchores.repository.room.ChoreRepoRoomImpl
import dev.smallcat.doyourchores.repository.room.ChoreRoomDatabase
import dev.smallcat.doyourchores.repository.room.Constants.Companion.CHORE_TABLE
import dev.smallcat.doyourchores.ui.home.HomeViewModel_HiltModules
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RoomDBModule {

    @Binds
    abstract fun bindIChoreRepository(choreRepo: ChoreRepoRoomImpl): iChoreRepository

    companion object {
        @Provides
        @Singleton
        fun provideChoreRoomDatabase(@ApplicationContext context: Context): ChoreRoomDatabase {
            return Room.databaseBuilder(
                context,
                ChoreRoomDatabase::class.java,
                CHORE_TABLE
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }

        @Provides
        fun provideChoreDao(choreRoomDatabase: ChoreRoomDatabase): ChoreDao {
            return choreRoomDatabase.choreDao()
        }

        @Provides
        fun provideEntity() = ChoreEntity()
    }
}