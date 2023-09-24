package dev.smallcat.doyourchores.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.smallcat.compose.DoYourChoresTheme
import dev.smallcat.doyourchores.ui.common.DYCScreen
import dev.smallcat.doyourchores.di.viewModelFactory
import dev.smallcat.doyourchores.ui.common.ChoreList
import dev.smallcat.doyourchores.ui.screens.home.views.Dashboard
import dev.smallcat.myapplication.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    homeVM: HomeViewModel = viewModel (
        factory = viewModelFactory { HomeViewModel() }
    )
) {

    val state by remember{homeVM.state}
    DYCScreen(
        modifier = modifier,
        title = stringResource(id = R.string.title_home),
        onBack = onBack
    ) {
        HomeScreenContent(
            state = state
        )
    }
}
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state : HomeUIState
) {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Dashboard()
            Divider(modifier = Modifier.padding(8.dp))
            ChoreList(list = state.chores)
        }
    }
}

@Preview
@Composable
private fun DefaultLightPreview() {
    DoYourChoresTheme {
        Surface {
            HomeScreenContent(state = HomeUIState())
        }
    }
}

@Preview
@Composable
private fun DefaultDarkPreview() {
    DoYourChoresTheme(true) {
        Surface {
            HomeScreenContent(state = HomeUIState())
        }
    }
}