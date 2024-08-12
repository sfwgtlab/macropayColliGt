package com.eduardocolli.Macropay.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduardocolli.Macropay.Adapters.MovieAdapter
import com.eduardocolli.Macropay.Models.Movie
import com.eduardocolli.Macropay.R
import com.eduardocolli.Macropay.ViewModels.HomeViewModel
import com.eduardocolli.Macropay.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {


    private lateinit var homeVM: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = resources.getColor(R.color.primary)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeVM = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeVM.getMovies(this)

        binding.pbHome.visibility = ProgressBar.VISIBLE
        homeVM.moviesResponse.observe(this, Observer { response ->
            if(response != null) initRecycler(response.results) else ShowToast("ha ocurrido un error, intente más tarde.")
        })

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(enabled = true){
            override fun handleOnBackPressed() {
                Toast.makeText(this@Home,"Back pressed", Toast.LENGTH_LONG).show()
                finish()
            }
        })

        binding.swiperefresh.setOnRefreshListener {
            homeVM.getMovies(this)
        }

        binding.IVHomeLogout.setOnClickListener {
            binding.pbHome.visibility = ProgressBar.VISIBLE
            logout()
        }






    }

    private fun ShowToast(mensaje: String){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    private fun initRecycler(movies: List<Movie>){
        binding.swiperefresh.isRefreshing = false
        binding.pbHome.visibility = ProgressBar.INVISIBLE
        binding.RvMoviesHome.layoutManager = LinearLayoutManager(this)
        val adapter = MovieAdapter(movies, this)
        binding.RvMoviesHome.adapter = adapter;

        adapter.onItemClick = { movie ->

            startActivity(Intent(this, MovieDetail::class.java).apply {
                putExtra("movie", movie)
            })
        }
    }

    private fun logout() {

        val sp = getSharedPreferences("user", MODE_PRIVATE);
        val editor: SharedPreferences.Editor =  sp.edit();

        editor.clear();
        editor.apply();
        val intent = Intent(this, MainActivity::class.java);
        startActivity(intent);
        finish();
    }





}