package com.reihan.finaljetmoviesapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reihan.finaljetmoviesapp.data.MoviesRepository
import com.reihan.finaljetmoviesapp.model.OrderMovie
import com.reihan.finaljetmoviesapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MoviesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderMovie>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<List<OrderMovie>>>
        get() = _uiState

    fun getAllMovies() {
        viewModelScope.launch {
            repository.getAllMovies()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderMovies ->
                    _uiState.value = UiState.Success(orderMovies)
                }
        }
    }
}