package com.eduardocolli.Macropay.Activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eduardocolli.Macropay.Models.Genre
import com.eduardocolli.Macropay.Models.Movie
import com.eduardocolli.Macropay.Models.detailResponse
import com.eduardocolli.Macropay.R
import com.eduardocolli.Macropay.ViewModels.MovieDetailViewModel
import com.eduardocolli.Macropay.databinding.ActivityMovieDetailBinding
import com.squareup.picasso.Picasso

class MovieDetail : AppCompatActivity() {

    private lateinit var movie: Movie
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var movieDetailVM: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(enabled = true){
            override fun handleOnBackPressed() {
                finish()
            }
        })

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        movieDetailVM = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        (intent?.extras?.getSerializable("movie") as Movie).let {
            movie = it
        }

        val imgUrl = resources.getString(R.string.img_domain)+movie.poster_path
        Picasso.get().load(imgUrl).into(binding.IVMovieDetailPoster)

        movieDetailVM.getGenres(this, movie.id.toString())

        movieDetailVM.genresResponse.observe(this, Observer { response ->
            binding.txtMDetailDuration.text = response.runtime.toString()+"min"
            binding.txtMDetailTitle.text = response.title
            if(response.adult)  binding.txtMDetailClassification.text = "R" else  binding.txtMDetailClassification.text = "A+"
            binding.txtMDetailRate.text = "%.2f".format(movie.vote_average)
            binding.txtMDetailSynopsisDesc.text = response.overview
            binding.txtMDetailRelease.text = "Release date: " + response.release_date
            var genresText = "Genres: "
            for (genre in response.genres){
                genresText += genre.name + " "
            }
            binding.txtMDetailGenres.text = genresText
        })



    }
}