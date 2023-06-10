package dev.smallcat.doyourchores.ui.home

import dev.smallcat.doyourchores.domain.Chore

data class HomeUIState(
    val chores: List<Chore> = listOf()
)