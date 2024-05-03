package dev.smallcat.doyourchores.ui.base

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import dev.smallcat.doyourchores.ui.navigation.BottomNavMenuItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DYCTopBar(topBarTitle: String, onBack: ()->Unit) {

    CenterAlignedTopAppBar(
        title = {
            Surface {
                Text(
                    text = topBarTitle,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        actions = {
            IconButton(onClick = { onBack() }) {
                Icon(
                    painterResource(id = BottomNavMenuItem.PROFILE.iconID),
                    contentDescription = BottomNavMenuItem.PROFILE.displayName
                )
            }
        }
    )
}