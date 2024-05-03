package dev.smallcat.doyourchores.domain

import dev.smallcat.doyourchores.data.repository.ChoreRepository
import dev.smallcat.doyourchores.data.repository.room.ChoreEntity
import dev.smallcat.doyourchores.common.models.Chore
import dev.smallcat.doyourchores.ui.screens.addChore.ChoreFormInput

interface AddChoreUseCase {
    suspend operator fun invoke(chore: ChoreFormInput)
}

class AddChoreUseCaseImpl (
    private val choreRepo: ChoreRepository
) : AddChoreUseCase {
    override suspend operator fun invoke(chore: ChoreFormInput) = run {
        val choreEntity = ChoreEntity(
            name = chore.name,
            description = chore.description,
            timeBetweenInDays = chore.daysBetween.toInt(),
            lastDone = chore.lastDoneDate
        )
        choreRepo.addChore(choreEntity)
    }
}