package com.reihan.finaljetmoviesapp.ui

import androidx.lifecycle.ViewModelProvider
import com.reihan.finaljetmoviesapp.data.MoviesRepository
import com.reihan.finaljetmoviesapp.ui.screen.cart.CartViewModel
import com.reihan.finaljetmoviesapp.ui.screen.detail.DetailMovieViewModel
import com.reihan.finaljetmoviesapp.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: MoviesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailMovieViewModel::class.java)) {
            return DetailMovieViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}