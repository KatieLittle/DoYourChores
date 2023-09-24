package dev.smallcat.doyourchores.data.repository.room

import dev.smallcat.doyourchores.domain.models.Chore
import dev.smallcat.doyourchores.data.repository.ChoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChoreRepoRoomImpl(private val choreDao: ChoreDao) : ChoreRepository {
    override fun getAllChores(): Flow<List<Chore>> = choreDao.getAllChores().map { choreList ->
        choreList.map { choreEntity ->
            Chore(
                name = choreEntity.name,
                description = choreEntity.description,
                timeBetweenInDays = choreEntity.timeBetweenInDays
            )
        }
    }

    override suspend fun addChore(chore: Chore) {
        val choreEntity = ChoreEntity(
            name = chore.name,
            description = chore.description,
            timeBetweenInDays = chore.timeBetweenInDays,
        )
        choreDao.addChore(choreEntity)
    }
}