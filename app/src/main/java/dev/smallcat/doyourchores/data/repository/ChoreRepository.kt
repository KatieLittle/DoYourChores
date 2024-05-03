package dev.smallcat.doyourchores.data.repository

import dev.smallcat.doyourchores.data.repository.room.ChoreEntity
import kotlinx.coroutines.flow.Flow

interface ChoreRepository {
    fun getAllChores(): Flow<List<ChoreEntity>>
    suspend fun addChore(chore : ChoreEntity)
}