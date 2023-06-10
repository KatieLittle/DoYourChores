package dev.smallcat.doyourchores.ui.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.smallcat.doyourchores.domain.FetchAllChoresUseCase
import dev.smallcat.doyourchores.domain.iFetchAllChoresUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    fetchAllChores: FetchAllChoresUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeUIState())
    val state = _state

    init {
        viewModelScope.launch {
            fetchAllChores().collect {
                _state.value = _state.value.copy(chores = it)
            }
        }
    }

}