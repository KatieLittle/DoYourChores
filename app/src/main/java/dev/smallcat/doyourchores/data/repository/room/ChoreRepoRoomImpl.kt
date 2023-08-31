package dev.smallcat.doyourchores.data.repository.room

import dev.smallcat.doyourchores.domain.models.Chore
import dev.smallcat.doyourchores.data.repository.ChoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChoreRepoRoomImpl(private val choreDao: ChoreDao) : ChoreRepository {
    override fun getAllChores(): Flow<List<Chore>> = flow {
        choreDao.getAllChores().collect{choreList ->
            emit(choreList.map {choreEntity ->
                Chore(
                    name = choreEntity.name,
                    description = choreEntity.description,
                    timeBetweenInDays = choreEntity.timeBetweenInDays
                )
            })
        }
    }
}