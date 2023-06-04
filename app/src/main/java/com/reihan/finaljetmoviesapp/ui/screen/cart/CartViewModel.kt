package com.reihan.finaljetmoviesapp.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reihan.finaljetmoviesapp.data.MoviesRepository
import com.reihan.finaljetmoviesapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: MoviesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderMovies() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderMovies()
                .collect { orderMovie ->
                    val totalRequiredPoint =
                        orderMovie.sumOf { it.movie.requiredMoney * it.count }
                    _uiState.value = UiState.Success(CartState(orderMovie, totalRequiredPoint))
                }
        }
    }

    fun updateOrderMovie(movieId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderMovie(movieId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderMovies()
                    }
                }
        }
    }
}