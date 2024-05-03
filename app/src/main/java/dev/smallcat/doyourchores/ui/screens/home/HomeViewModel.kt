package dev.smallcat.doyourchores.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.smallcat.doyourchores.DYCApp
import dev.smallcat.doyourchores.domain.FetchAllChoresUseCase
import dev.smallcat.doyourchores.common.models.Chore
import kotlinx.coroutines.launch
data class HomeUIState(
    val chores: List<Chore> = listOf()
)

class HomeViewModel(
    fetchAllChoresUseCase: FetchAllChoresUseCase = DYCApp.choreModule.fetchAllChoresUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeUIState())
    val state = _state

    init {
        viewModelScope.launch {
            fetchAllChoresUseCase().collect {
                _state.value = _state.value.copy(chores = it)
            }
        }
    }
}