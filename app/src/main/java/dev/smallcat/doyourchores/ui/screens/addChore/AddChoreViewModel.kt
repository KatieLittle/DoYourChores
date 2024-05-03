package dev.smallcat.doyourchores.ui.screens.addChore


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.smallcat.doyourchores.DYCApp
import dev.smallcat.doyourchores.domain.AddChoreUseCase
import dev.smallcat.doyourchores.common.models.Chore
import kotlinx.coroutines.launch
import java.time.LocalDate

data class AddChoreUIState(
    val title: String = "Add new chore",
    val isDatePickerShown: Boolean = false,
    val newChore: ChoreFormInput = ChoreFormInput()
)

data class ChoreFormInput(
    var name: String = "",
    var description: String = "",
    var daysBetween: String = "",
    var lastDoneDate: LocalDate = LocalDate.now()
)

sealed class AddChoreScreenEvent {
    object SaveChore: AddChoreScreenEvent()
    data class UpdateChore(
        val chore: ChoreFormInput
    ): AddChoreScreenEvent()
    object DateTapped: AddChoreScreenEvent()
    object DateDismissed: AddChoreScreenEvent()
}

class AddChoreViewModel(
    val addChoreUseCase: AddChoreUseCase = DYCApp.choreModule.addChoreUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AddChoreUIState())
    val state = _state

    fun onEvent(event: AddChoreScreenEvent) {
        when (event) {
            is AddChoreScreenEvent.SaveChore -> handleSaveChore()
            is AddChoreScreenEvent.UpdateChore -> handleUpdateChore(event.chore)
            is AddChoreScreenEvent.DateTapped -> handleDatePickerToggle(true)
            is AddChoreScreenEvent.DateDismissed -> handleDatePickerToggle(false)
        }
    }

    private fun handleUpdateChore(chore: ChoreFormInput) {
        _state.value = _state.value.copy(
            newChore = chore
        )
    }

    private fun handleDatePickerToggle(showPicker: Boolean) {
        _state.value = _state.value.copy(
            isDatePickerShown = showPicker
        )
    }

    private fun handleSaveChore() {
        viewModelScope.launch {
            addChoreUseCase(state.value.newChore)
        }
    }
}