package dev.smallcat.doyourchores.ui.screens.home

import dev.smallcat.doyourchores.domain.models.Chore

data class HomeUIState(
    val chores: List<Chore> = listOf()
)