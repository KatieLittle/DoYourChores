package dev.smallcat.doyourchores.domain

import dev.smallcat.doyourchores.data.repository.ChoreRepository
import dev.smallcat.doyourchores.domain.models.Chore

interface AddChoreUseCase {
    suspend operator fun invoke(chore: Chore)
}

class AddChoreUseCaseImpl (
    private val choreRepo: ChoreRepository
) : AddChoreUseCase {
    override suspend operator fun invoke(chore: Chore) = choreRepo.addChore(chore)
}