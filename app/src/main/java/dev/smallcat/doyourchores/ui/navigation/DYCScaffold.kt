package dev.smallcat.doyourchores.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.smallcat.compose.DoYourChoresTheme
import dev.smallcat.doyourchores.ui.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DYCScaffold() {

    val navController = rememberNavController()

    Scaffold(
        topBar = { DYCTopBar(navController) },
        bottomBar = {
            BottomMenu(navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavMenuItem.HOME.route,
            modifier = Modifier.padding(it)
        ) {
            composable(BottomNavMenuItem.HOME.route) { HomeScreen() }
            composable(BottomNavMenuItem.CHORES.route) { }
            composable(BottomNavMenuItem.PROFILE.route) { }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DYCTopBar(navController: NavHostController) {
    CenterAlignedTopAppBar(
        title = {
            Surface {
                Text(text = "Home")
            }
        },
        actions = {
            IconButton(onClick = { navController.navigate(BottomNavMenuItem.PROFILE.route) }) {
                Icon(
                    painterResource(id = BottomNavMenuItem.PROFILE.iconID),
                    contentDescription = BottomNavMenuItem.PROFILE.displayName
                )
            }
        }
    )
}

@Composable
fun BottomMenu(navController: NavHostController, modifier: Modifier = Modifier){

    var selectedItemIndex by remember{ mutableStateOf(0)}
    val items = BottomNavMenuItem.values()

    NavigationBar(
        modifier = modifier
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.iconID), contentDescription = item.displayName) },
                label = { Text(item.displayName) },
                selected =  (selectedItemIndex == index),
                onClick = {
                    selectedItemIndex = index
                    when(index){
                        BottomNavMenuItem.HOME.ordinal -> navController.navigate(BottomNavMenuItem.HOME.route)
                        BottomNavMenuItem.CHORES.ordinal -> navController.navigate(BottomNavMenuItem.CHORES.route)
                        BottomNavMenuItem.PROFILE.ordinal -> navController.navigate(BottomNavMenuItem.PROFILE.route)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun Preview_scaffold_light() {
    DoYourChoresTheme {
        DYCScaffold()
    }
}

@Preview
@Composable
fun Preview_scaffold_dark() {
    DoYourChoresTheme(true) {
        DYCScaffold()
    }
}