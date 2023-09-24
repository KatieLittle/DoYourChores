package dev.smallcat.doyourchores.domain

import dev.smallcat.doyourchores.data.repository.ChoreRepository
import dev.smallcat.doyourchores.domain.models.Chore
import kotlinx.coroutines.flow.Flow

interface FetchAllChoresUseCase {
    operator fun invoke(): Flow<List<Chore>>
}

class FetchAllChoresUseCaseImpl (
    private val choreRepo: ChoreRepository
) : FetchAllChoresUseCase {
    override operator fun invoke(): Flow<List<Chore>> = choreRepo.getAllChores()
}