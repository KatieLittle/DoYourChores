package dev.smallcat.doyourchores.common.models

import java.time.LocalDate

data class Chore (
    val name: String,
    val description: String,
    val lastDoneDate: LocalDate,
    val timeBetweenInDays: Int,
    val dueState: DueState = DueState.UPCOMING,
    val dueDateDescription: String = ""
)
