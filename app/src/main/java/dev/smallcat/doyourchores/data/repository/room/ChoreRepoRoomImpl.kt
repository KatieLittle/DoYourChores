package dev.smallcat.doyourchores.data.repository.room

import dev.smallcat.doyourchores.data.repository.ChoreRepository
import kotlinx.coroutines.flow.Flow

class ChoreRepoRoomImpl(private val choreDao: ChoreDao) : ChoreRepository {
    override fun getAllChores(): Flow<List<ChoreEntity>> = choreDao.getAllChores()

    override suspend fun addChore(chore: ChoreEntity) {
        choreDao.addChore(chore)
    }
}