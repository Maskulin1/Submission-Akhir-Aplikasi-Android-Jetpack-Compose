package com.reihan.finaljetmoviesapp.data

import com.reihan.finaljetmoviesapp.model.MoviesDataSource
import com.reihan.finaljetmoviesapp.model.OrderMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MoviesRepository {

    private val orderMovies = mutableListOf<OrderMovie>()

    init {
        if (orderMovies.isEmpty()) {
            MoviesDataSource.dummyMovies.forEach {
                orderMovies.add(OrderMovie(it, 0))
            }
        }
    }

    fun getAllMovies(): Flow<List<OrderMovie>> {
        return flowOf(orderMovies)
    }

    fun getOrderMovieById(movieId: Long): OrderMovie {
        return orderMovies.first {
            it.movie.id == movieId
        }
    }

    fun updateOrderMovie(movieId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderMovies.indexOfFirst { it.movie.id == movieId }
        val result = if (index >= 0) {
            val orderMovie = orderMovies[index]
            orderMovies[index] =
                orderMovie.copy(movie = orderMovie.movie, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderMovies(): Flow<List<OrderMovie>> {
        return getAllMovies()
            .map { orderMovies ->
                orderMovies.filter { orderMovie ->
                    orderMovie.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: MoviesRepository? = null

        fun getInstance(): MoviesRepository =
            instance ?: synchronized(this) {
                MoviesRepository().apply {
                    instance = this
                }
            }
    }

}