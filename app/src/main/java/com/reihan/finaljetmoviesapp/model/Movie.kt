package com.reihan.finaljetmoviesapp.model

data class Movie (
    var id: Long,
    var photo: String,
    var title: String,
    var genre: String,
    var duration: String,
    var year: String,
    var platform: String,
    var rating: String,
    var director: String,
    var cast: String,
    var description: String,
    var requiredMoney: Int,
)