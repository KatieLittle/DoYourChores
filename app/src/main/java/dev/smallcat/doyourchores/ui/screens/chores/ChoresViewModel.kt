package dev.smallcat.doyourchores.ui.screens.chores

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.smallcat.doyourchores.DYCApp
import dev.smallcat.doyourchores.domain.AddChoreUseCase
import dev.smallcat.doyourchores.domain.FetchAllChoresUseCase
import dev.smallcat.doyourchores.domain.models.Chore
import kotlinx.coroutines.launch

sealed class ChoreScreenUIEvent {
    data class OpenAddChoreForm(val chore: Chore): ChoreScreenUIEvent()
}
class ChoresViewModel(
    fetchAllChoresUseCase: FetchAllChoresUseCase = DYCApp.choreModule.fetchAllChoresUseCase,
    val addChoreUseCase: AddChoreUseCase = DYCApp.choreModule.addChoreUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ChoresUIState())
    val state = _state

    init {
        viewModelScope.launch {
            fetchAllChoresUseCase().collect {
                _state.value = _state.value.copy(chores = it)
            }
        }
    }

    fun onEvent(event: ChoreScreenUIEvent) {
        when(event) {
            is ChoreScreenUIEvent.OpenAddChoreForm -> handleOpenAddChoreForm(event.chore)
        }
    }

    private fun handleOpenAddChoreForm(chore: Chore) {
        viewModelScope.launch {
            addChoreUseCase(chore)
        }
    }


}