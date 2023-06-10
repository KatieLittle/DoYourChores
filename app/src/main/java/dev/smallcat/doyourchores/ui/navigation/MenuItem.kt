package dev.smallcat.doyourchores.ui.navigation

import dev.smallcat.myapplication.R


interface MenuItem {
    val displayName: String
    val iconID: Int
    val route: String
}

enum class BottomNavMenuItem(
    override val displayName: String,
    override val iconID: Int,
    override val route: String
) : MenuItem {
    HOME("Home", R.drawable.ic_home, "home"),
    CHORES("Chores", R.drawable.ic_chore, "chores"),
    PROFILE("Profile", R.drawable.ic_account, "profile")
}

enum class MicsNavMenuItem(
    override val displayName: String,
    override val iconID: Int,
    override val route: String
) : MenuItem {
    SETTINGS("Settings", R.drawable.ic_settings, "settings")
}
