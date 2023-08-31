package dev.smallcat.doyourchores.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.smallcat.doyourchores.DYCApp
import dev.smallcat.doyourchores.domain.FetchAllChoresUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    fetchAllChoresUseCase: FetchAllChoresUseCase = DYCApp.appModule.fetchAllChoresUseCase
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