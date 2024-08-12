package com.eduardocolli.Macropay.Models


data class MoviesResponse (
    var dates: dates,
    var page: Number,
    var results: List<Movie>,
    var total_pages: Int,
    var total_results: Int
)