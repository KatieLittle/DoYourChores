package dev.smallcat.doyourchores.ui.screens.chores

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.smallcat.doyourchores.DYCApp
import dev.smallcat.doyourchores.domain.FetchAllChoresUseCase
import dev.smallcat.doyourchores.common.models.Chore
import kotlinx.coroutines.launch
data class ChoresUIState(
    val chores: List<Chore> = listOf(),
    val showAddChorePopup: Boolean = false
)
sealed class ChoreScreenUIEvent {
    data object OpenAddChoreForm: ChoreScreenUIEvent()
    data object CloseAddChoreForm: ChoreScreenUIEvent()
}
class ChoresViewModel(
    fetchAllChoresUseCase: FetchAllChoresUseCase = DYCApp.choreModule.fetchAllChoresUseCase,
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
            is ChoreScreenUIEvent.OpenAddChoreForm -> handleOpenAddChoreForm()
            is ChoreScreenUIEvent.CloseAddChoreForm -> handleCloseAddChoreForm()
        }
    }


    private fun handleCloseAddChoreForm() {
        _state.value = _state.value.copy(showAddChorePopup = false)
    }

    private fun handleOpenAddChoreForm() {
        _state.value = _state.value.copy(showAddChorePopup = true)
    }

}