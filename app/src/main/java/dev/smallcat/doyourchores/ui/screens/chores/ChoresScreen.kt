package dev.smallcat.doyourchores.ui.screens.chores

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.smallcat.compose.DoYourChoresTheme
import dev.smallcat.doyourchores.ui.common.DYCScreen
import dev.smallcat.doyourchores.domain.models.Chore
import dev.smallcat.doyourchores.di.viewModelFactory
import dev.smallcat.doyourchores.ui.common.ChoreList
import dev.smallcat.myapplication.R

@Composable
fun ChoresScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    choresVM: ChoresViewModel = viewModel (
        factory = viewModelFactory { ChoresViewModel() }
    )
) {

    val state by remember{choresVM.state}
    DYCScreen(
        title = stringResource(id = R.string.title_chores),
        onBack = onBack
    ) {
        ChoreScreenContent(
            modifier = modifier,
            state = state,
            onEvent = choresVM::onEvent
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoreScreenContent(modifier: Modifier = Modifier, state: ChoresUIState, onEvent: (ChoreScreenUIEvent)->Unit){
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            AddChoreFAB(
                onClick = { chore ->
                    onEvent(ChoreScreenUIEvent.OpenAddChoreForm(chore))
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ChoreList(list = state.chores)
            }
        }
    }
}
@Composable
fun AddChoreFAB(onClick: (Chore) -> Unit){
    FloatingActionButton(
        onClick = {
            onClick(
                Chore(
                    name = "Hoover",
                    description = "It's hoovering, have fun",
                    timeBetweenInDays = 7.0
                )
            )
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = "Add Chore"
        )
    }
}

private val previewState = ChoresUIState(
    chores = listOf(
        Chore(
            name = "Water Plants",
            description = "Do it!",
            timeBetweenInDays = 7.0
        )
    )
)

@Preview
@Composable
private fun DefaultLightPreview() {
    DoYourChoresTheme {
        Surface {
            ChoreScreenContent(
                state = previewState,
                onEvent = {}
            )
        }
    }
}

@Preview
@Composable
private fun DefaultDarkPreview() {
    DoYourChoresTheme(true) {
        Surface {
            ChoreScreenContent(
                state = previewState,
                onEvent = {}
            )
        }
    }
}
