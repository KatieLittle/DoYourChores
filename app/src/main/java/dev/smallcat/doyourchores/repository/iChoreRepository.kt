package dev.smallcat.doyourchores.repository

import dev.smallcat.doyourchores.domain.Chore
import kotlinx.coroutines.flow.Flow

interface iChoreRepository {
    fun getAllChores(): Flow<List<Chore>>
}