package dev.smallcat.doyourchores.ui.home.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dev.smallcat.doyourchores.domain.FetchAllChoresUseCase
import dev.smallcat.doyourchores.domain.iFetchAllChoresUseCase
import dev.smallcat.doyourchores.repository.iChoreRepository
import dev.smallcat.doyourchores.repository.room.ChoreDao
import dev.smallcat.doyourchores.repository.room.ChoreRoomDatabase

@Module
@InstallIn(ActivityComponent::class)
abstract class HomeViewModelModule {

    @Binds
    abstract fun bindFetchAllChoresUseCase(
        fetchAllChores: FetchAllChoresUseCase
    ): iFetchAllChoresUseCase

    companion object {
        @Provides
        fun provideFetchAllChoresUseCase(choreRepo: iChoreRepository): FetchAllChoresUseCase {
            return FetchAllChoresUseCase(choreRepo)
        }
    }

}