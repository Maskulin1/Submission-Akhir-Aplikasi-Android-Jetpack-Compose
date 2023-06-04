package com.reihan.finaljetmoviesapp.di

import com.reihan.finaljetmoviesapp.data.MoviesRepository

object Injection {
    fun provideRepository(): MoviesRepository {
        return MoviesRepository.getInstance()
    }
}