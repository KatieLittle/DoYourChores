package dev.smallcat.doyourchores.ui.screens.addChore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import dev.smallcat.doyourchores.di.viewModelFactory
import dev.smallcat.doyourchores.ui.base.DYCDatePicker
import dev.smallcat.doyourchores.ui.base.DYCPreview
import dev.smallcat.myapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddChoreScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    addChoreVM: AddChoreViewModel = viewModel(factory = viewModelFactory { AddChoreViewModel() })
) {

    val state by remember { addChoreVM.state }

    Dialog(
        onDismissRequest = onBack,
    ) {
        AddChoreScreenContent(
            modifier = modifier, state = state, onEvent = addChoreVM::onEvent, onBack = onBack
        )
    }
    if (state.isDatePickerShown) {
        DYCDatePicker(onDismiss = {
            addChoreVM.onEvent(AddChoreScreenEvent.DateDismissed)
        }, onDateSelected = {
            addChoreVM.onEvent(
                AddChoreScreenEvent.UpdateChore(state.newChore.copy(lastDoneDate = it))
            )
        })
    }
}

@Composable
fun AddChoreScreenContent(
    modifier: Modifier = Modifier,
    state: AddChoreUIState,
    onEvent: (AddChoreScreenEvent) -> Unit,
    onBack: () -> Unit
) {
    Card {
        ChoreForm(
            modifier = modifier, state = state, onEvent = onEvent, onBack = onBack
        )
    }
}

@Composable
fun ChoreForm(
    modifier: Modifier = Modifier,
    state: AddChoreUIState,
    onEvent: (AddChoreScreenEvent) -> Unit,
    onBack: () -> Unit,
) {

    Column(
        modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = state.title, style = MaterialTheme.typography.titleMedium
        )
        InputField(
            label = stringResource(R.string.name_input_label),
            value = state.newChore.name,
            onValueChange = {
                onEvent(AddChoreScreenEvent.UpdateChore(state.newChore.copy(name = it)))
            },

            )
        InputField(
            label = stringResource(R.string.days_between_input_label),
            value = state.newChore.daysBetween,
            onValueChange = {
                onEvent(AddChoreScreenEvent.UpdateChore(state.newChore.copy(daysBetween = it)))
            },
        )

        LastDoneDate(label = stringResource(R.string.last_done_input_label),
            value = state.newChore.lastDoneDate.toString(),
            openDatePicker = {
                onEvent(AddChoreScreenEvent.DateTapped)
            })
        InputField(
            label = stringResource(R.string.description_input_label),
            value = state.newChore.description,
            onValueChange = {
                onEvent(AddChoreScreenEvent.UpdateChore(state.newChore.copy(description = it)))
            },
        )
        ButtonRow(
            saveChore = {
                onEvent(AddChoreScreenEvent.SaveChore)
            }, onBack = onBack
        )
    }
}

@Composable
private fun InputField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(label)
        })
}

@Composable
private fun LastDoneDate(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    openDatePicker: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    if (interactionSource.collectIsPressedAsState().value) {
        openDatePicker()
    }

    OutlinedTextField(modifier = modifier
        .fillMaxWidth()
        .clickable { openDatePicker() },
        value = value,
        label = {
            Text(label)
        },
        onValueChange = {},
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit_calendar),
                contentDescription = "edit date"
            )
        },
        interactionSource = interactionSource,
        readOnly = true
    )
}

@Composable
private fun ButtonRow(
    saveChore: () -> Unit, onBack: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
    ) {
        TextButton(onClick = {
            onBack()
        }) {
            Text(stringResource(R.string.cancel))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = {
            saveChore()
            onBack()
        }) {
            Text(stringResource(R.string.label_save))
        }

    }
}


private val previewState = AddChoreUIState()

@Preview
@Composable
private fun DefaultLightPreview() {
    AppTheme {
        AddChoreScreenContent(
            state = previewState,
            onEvent = {},
            onBack = {},
        )
    }
}

@Preview
@Composable
private fun DefaultDarkPreview() {
    AppTheme(true) {
        AddChoreScreenContent(state = previewState, onEvent = {}, onBack = {})
    }
}

@Preview
@Composable
private fun ScreenLightPreview() {
    DYCPreview() {
        AddChoreScreenContent(state = previewState, onEvent = {}, onBack = {})
    }
}

@Preview
@Composable
private fun ScreenDarkPreview() {
    DYCPreview(true) {
        AddChoreScreenContent(
            state = previewState,
            onEvent = {},
            onBack = {},
        )
    }
}
