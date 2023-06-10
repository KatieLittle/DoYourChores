package dev.smallcat.doyourchores.domain

data class Chore (
    val name: String,
    val description: String,
    val timeBetweenInDays: Double
)
