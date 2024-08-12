package com.eduardocolli.Macropay.Models

import java.io.Serializable

data class Movie(
    var adult: Boolean,
    var backdrop_path: String,
    var genre_ids: List<Number>,
    var id: Number,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Number,
    var poster_path: String,
    var release_date: String,
    var title: String,
    var video: Boolean,
    var vote_average: Float,
    var vote_count: Number
): Serializable
