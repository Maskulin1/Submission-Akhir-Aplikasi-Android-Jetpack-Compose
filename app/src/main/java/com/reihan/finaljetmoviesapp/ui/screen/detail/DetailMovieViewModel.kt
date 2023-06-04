package com.reihan.finaljetmoviesapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reihan.finaljetmoviesapp.data.MoviesRepository
import com.reihan.finaljetmoviesapp.model.Movie
import com.reihan.finaljetmoviesapp.model.OrderMovie
import com.reihan.finaljetmoviesapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val repository: MoviesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderMovie>> =
        MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<OrderMovie>>
        get() = _uiState

    fun getMovieById(movieId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderMovieById(movieId))
        }
    }

    fun addToCart(movie: Movie, count: Int) {
        viewModelScope.launch {
            repository.updateOrderMovie(movie.id, count)
        }
    }
}