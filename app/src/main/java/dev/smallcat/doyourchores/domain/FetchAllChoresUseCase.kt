package dev.smallcat.doyourchores.domain

import dev.smallcat.doyourchores.data.repository.ChoreRepository
import dev.smallcat.doyourchores.common.models.Chore
import dev.smallcat.doyourchores.common.models.DueState
import dev.smallcat.doyourchores.common.utils.formatDateLocally
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit

interface FetchAllChoresUseCase {
    operator fun invoke(): Flow<List<Chore>>
}

class FetchAllChoresUseCaseImpl (
    private val choreRepo: ChoreRepository
) : FetchAllChoresUseCase {
    override operator fun invoke(): Flow<List<Chore>> = choreRepo.getAllChores().map { entityList ->
        entityList.map { entity ->

            val dueState = determineChoreDueState(
                entity.lastDone,
                entity.timeBetweenInDays
            )

            val dueDateDescription = determineDueDateDescriptionWording(
                entity.lastDone,
                entity.timeBetweenInDays
            )

            Chore(
                name = entity.name,
                description = entity.description,
                timeBetweenInDays = entity.timeBetweenInDays,
                lastDoneDate = entity.lastDone,
                dueState = dueState,
                dueDateDescription = dueDateDescription
            )
        }
    }

    private fun determineChoreDueState(
        lastDoneDate: LocalDate,
        daysBetween: Int,
        today: LocalDate = LocalDate.now()
    ): DueState {
        val dueDate = lastDoneDate.plusDays(daysBetween.toLong())
        val daysUntil = today.until(dueDate, ChronoUnit.DAYS)
        return when {
            daysUntil < 0L -> DueState.OVERDUE
            daysUntil == 0L -> DueState.TODAY
            else -> DueState.UPCOMING
        }
    }

    private fun determineDueDateDescriptionWording(
        lastDoneDate: LocalDate,
        daysBetween: Int,
        today: LocalDate = LocalDate.now()
    ): String  {
        val dueDate = lastDoneDate.plusDays(daysBetween.toLong())
        val yesterday = today.minusDays(1)
        val tomorrow = today.plusDays(1)

        return when(dueDate) {
            yesterday -> "Yesterday"
            today -> "Today"
            tomorrow -> "Tomorrow"
            else -> formatDateLocally(dueDate, FormatStyle.SHORT)
        }
    }

}