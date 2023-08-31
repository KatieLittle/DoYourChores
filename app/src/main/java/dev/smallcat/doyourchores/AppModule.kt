package dev.smallcat.doyourchores

import android.content.Context
import dev.smallcat.doyourchores.data.repository.room.ChoreRepoRoomImpl
import dev.smallcat.doyourchores.data.repository.room.di.ChoreRoomModule
import dev.smallcat.doyourchores.data.repository.room.di.ChoreRoomModuleImpl
import dev.smallcat.doyourchores.domain.FetchAllChoresUseCase
import dev.smallcat.doyourchores.domain.FetchAllChoresUseCaseImpl

interface AppModule {
    val choreRoomModule: ChoreRoomModule
    val fetchAllChoresUseCase: FetchAllChoresUseCase
}
class AppModuleImpl(
    private val appContext: Context,
) : AppModule {
    override val choreRoomModule: ChoreRoomModule
        get() = ChoreRoomModuleImpl(appContext)

    override val fetchAllChoresUseCase: FetchAllChoresUseCase
        get() = FetchAllChoresUseCaseImpl(ChoreRepoRoomImpl(choreRoomModule.dao))
}