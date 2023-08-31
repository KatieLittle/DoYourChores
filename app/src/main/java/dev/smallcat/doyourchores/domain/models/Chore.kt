package dev.smallcat.doyourchores.domain.models

data class Chore (
    val name: String,
    val description: String,
    val timeBetweenInDays: Double
)
