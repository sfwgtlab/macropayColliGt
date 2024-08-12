package com.eduardocolli.Macropay.Services

import com.eduardocolli.Macropay.Models.Genres
import com.eduardocolli.Macropay.Models.MoviesResponse
import com.eduardocolli.Macropay.Models.detailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Url

interface moviesService {

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMzgxODdjNWNmZjc3ZjRmZmNmNmZjNWY3MzMzNjUxYSIsIm5iZiI6MTcyMzI1NjY4My43NzkyMzIsInN1YiI6IjY2YjZjZTM0MTM3MzM3NTM4YjdhZDVhNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7yCw46f_d4fuho92cwx921bYPO-4xqIiv0AkI2I-ASQ"
    )
    @GET
    suspend fun getMovies(@Url url: String): Response<MoviesResponse>

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiMzgxODdjNWNmZjc3ZjRmZmNmNmZjNWY3MzMzNjUxYSIsIm5iZiI6MTcyMzI1NjY4My43NzkyMzIsInN1YiI6IjY2YjZjZTM0MTM3MzM3NTM4YjdhZDVhNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.7yCw46f_d4fuho92cwx921bYPO-4xqIiv0AkI2I-ASQ"
    )
    @GET
    suspend fun getGenres(@Url url: String): Response<detailResponse>



}