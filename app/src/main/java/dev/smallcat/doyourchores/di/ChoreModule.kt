package dev.smallcat.doyourchores.di

import android.content.Context
import dev.smallcat.doyourchores.data.repository.room.ChoreRepoRoomImpl
import dev.smallcat.doyourchores.data.repository.room.di.ChoreRoomModule
import dev.smallcat.doyourchores.data.repository.room.di.ChoreRoomModuleImpl
import dev.smallcat.doyourchores.domain.AddChoreUseCase
import dev.smallcat.doyourchores.domain.AddChoreUseCaseImpl
import dev.smallcat.doyourchores.domain.FetchAllChoresUseCase
import dev.smallcat.doyourchores.domain.FetchAllChoresUseCaseImpl

interface ChoreModule {
    val fetchAllChoresUseCase: FetchAllChoresUseCase
    val addChoreUseCase: AddChoreUseCase
}
class ChoreModuleImpl(
    context: Context,
) : ChoreModule {
    private val choreRoomModule: ChoreRoomModule = ChoreRoomModuleImpl(context)

    override val fetchAllChoresUseCase: FetchAllChoresUseCase by lazy { FetchAllChoresUseCaseImpl(
        ChoreRepoRoomImpl(choreRoomModule.db.choreDao())
    ) }

    override val addChoreUseCase: AddChoreUseCase by lazy { AddChoreUseCaseImpl(ChoreRepoRoomImpl(choreRoomModule.db.choreDao())) }
}