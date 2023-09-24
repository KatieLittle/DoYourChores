package dev.smallcat.doyourchores.ui.screens.chores

import dev.smallcat.doyourchores.domain.models.Chore

data class ChoresUIState(
    val chores: List<Chore> = listOf()
)