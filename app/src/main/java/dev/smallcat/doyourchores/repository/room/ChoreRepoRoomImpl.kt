package dev.smallcat.doyourchores.repository.room

import dev.smallcat.doyourchores.domain.Chore
import dev.smallcat.doyourchores.repository.iChoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChoreRepoRoomImpl @Inject constructor(private val choreDao: ChoreDao) : iChoreRepository {
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