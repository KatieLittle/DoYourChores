package dev.smallcat.doyourchores.ui.screens.chores

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import dev.smallcat.doyourchores.ui.base.DYCScreen
import dev.smallcat.doyourchores.common.models.Chore
import dev.smallcat.doyourchores.common.models.DueState
import dev.smallcat.doyourchores.di.viewModelFactory
import dev.smallcat.doyourchores.ui.components.ChoreList
import dev.smallcat.doyourchores.ui.screens.addChore.AddChoreScreen
import dev.smallcat.myapplication.R
import java.time.LocalDate

@Composable
fun ChoresScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    choresVM: ChoresViewModel = viewModel(factory = viewModelFactory { ChoresViewModel() })
) {

    val state = remember { choresVM.state }

    DYCScreen(
        title = stringResource(id = R.string.title_chores), onBack = onBack
    ) {
        ChoreScreenContent(
            modifier = modifier, state = state.value, onEvent = choresVM::onEvent
        )
        if (state.value.showAddChorePopup) {
            AddChoreScreen(
                onBack = {
                    choresVM.onEvent(ChoreScreenUIEvent.CloseAddChoreForm)
                }
            )
        }
    }
}

@Composable
fun ChoreScreenContent(
    modifier: Modifier = Modifier,
    state: ChoresUIState,
    onEvent: (ChoreScreenUIEvent) -> Unit
) {
    Scaffold(modifier = modifier, floatingActionButton = {
        AddChoreFAB(onClick = {
            onEvent(ChoreScreenUIEvent.OpenAddChoreForm)
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ChoreList(list = state.chores)
        }
    }
}

@Composable
fun AddChoreFAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_plus), contentDescription = "Add Chore"
        )
    }
}

private val previewState = ChoresUIState(
    chores = listOf(
        Chore(
            name = "Water Plants",
            description = "Do it!",
            timeBetweenInDays = 7,
            lastDoneDate = LocalDate.now(),
            dueState = DueState.OVERDUE,
            dueDateDescription = "Yesterday"
        )
    )
)

@Preview
@Composable
private fun DefaultLightPreview() {
    AppTheme {
        Surface {
            ChoreScreenContent(state = previewState, onEvent = {})
        }
    }
}

@Preview
@Composable
private fun DefaultDarkPreview() {
    AppTheme(true) {
        Surface {
            ChoreScreenContent(state = previewState, onEvent = {})
        }
    }
}
