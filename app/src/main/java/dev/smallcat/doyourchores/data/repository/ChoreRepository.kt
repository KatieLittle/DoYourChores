package dev.smallcat.doyourchores.data.repository

import dev.smallcat.doyourchores.domain.models.Chore
import kotlinx.coroutines.flow.Flow

interface ChoreRepository {
    fun getAllChores(): Flow<List<Chore>>
    suspend fun addChore(chore : Chore)
}