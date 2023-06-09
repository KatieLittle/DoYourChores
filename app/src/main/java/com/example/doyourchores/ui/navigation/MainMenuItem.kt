package com.example.doyourchores.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainMenuItem(val displayName: String, val icon: ImageVector, val route: String){
    HOME("Home", Icons.Filled.Home, "home"),
    CHORES("Chores", Icons.Filled.Clear, "chores"),
    PROFILE("Profile", Icons.Filled.Person, "person")
}