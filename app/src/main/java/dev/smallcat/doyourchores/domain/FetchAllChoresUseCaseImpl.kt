package dev.smallcat.doyourchores.domain

import dev.smallcat.doyourchores.DYCApp
import dev.smallcat.doyourchores.data.repository.ChoreRepository
import dev.smallcat.doyourchores.data.repository.room.ChoreRepoRoomImpl
import dev.smallcat.doyourchores.domain.models.Chore
import kotlinx.coroutines.flow.Flow

interface FetchAllChoresUseCase {
    operator fun invoke(): Flow<List<Chore>>
}

class FetchAllChoresUseCaseImpl (
    private val choreRepo: ChoreRepository = ChoreRepoRoomImpl(DYCApp.appModule.choreRoomModule.dao)
) : FetchAllChoresUseCase {
    override operator fun invoke(): Flow<List<Chore>> = choreRepo.getAllChores()
}