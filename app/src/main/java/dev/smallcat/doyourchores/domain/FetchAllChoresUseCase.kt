package dev.smallcat.doyourchores.domain

import dev.smallcat.doyourchores.repository.iChoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface iFetchAllChoresUseCase {
    operator fun invoke(): Flow<List<Chore>>
}

class FetchAllChoresUseCase @Inject constructor(
    private val choreRepo: iChoreRepository
) : iFetchAllChoresUseCase {
    override operator fun invoke(): Flow<List<Chore>> = choreRepo.getAllChores()
}