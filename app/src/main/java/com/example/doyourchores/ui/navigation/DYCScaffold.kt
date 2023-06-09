package com.example.doyourchores.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doyourchores.ui.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DYCScaffold() {

    val navController = rememberNavController()

    Scaffold(
        topBar = { DYCTopBar() },
        bottomBar = {
            BottomMenu(navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = MainMenuItem.HOME.route,
            modifier = Modifier.padding(it)
        ) {
            composable(MainMenuItem.HOME.route) { HomeScreen() }
            composable(MainMenuItem.CHORES.route) {  }
            composable(MainMenuItem.PROFILE.route) {  }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DYCTopBar(){
    CenterAlignedTopAppBar(
        title = {
            Surface() {
                Text(text = "Home")
            }
        },
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@Composable
fun BottomMenu(navController: NavHostController, modifier: Modifier = Modifier){

    var selectedItemIndex by remember{ mutableStateOf(0)}
    val items = MainMenuItem.values()

    NavigationBar(
        modifier = modifier
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.displayName) },
                label = { Text(item.displayName) },
                selected =  (selectedItemIndex == index),
                onClick = {
                    selectedItemIndex = index
                    when(index){
                        MainMenuItem.HOME.ordinal -> navController.navigate(MainMenuItem.HOME.route)
                        MainMenuItem.CHORES.ordinal -> navController.navigate(MainMenuItem.CHORES.route)
                        MainMenuItem.PROFILE.ordinal -> navController.navigate(MainMenuItem.PROFILE.route)
                    }
                }
            )
        }
    }
}