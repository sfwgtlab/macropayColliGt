package com.eduardocolli.Macropay.Models

data class detailResponse(
    var adult: Boolean,
    var backdrop_path: String,
    var belongs_to_collection: collection,
    var budget: Long,
    var genres: List<Genre>,
    var homepage: String,
    var id: Number,
    var imdb_id: String,
    var origin_country: List<String>,
    var original_language: String,
    val original_title: String,
    var overview: String,
    var popularity: Float,
    var poster_path: String,
    var production_companies: List<companies>,
    var production_countries: List<country>,
    var spoken_languages: List<lenguage>,
    var release_date: String,
    var revenue: Float,
    var runtime: Number,
    var status: String,
    var tagline: String,
    var title: String,
    var video: String,
    var vote_average: Number,
    var vote_count: Number


)
