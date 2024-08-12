package com.eduardocolli.Macropay.ViewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardocolli.Macropay.Models.MoviesResponse
import com.eduardocolli.Macropay.Services.moviesService
import com.eduardocolli.Macropay.Services.retrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel: ViewModel() {

    var moviesResponse: MutableLiveData<MoviesResponse?> = MutableLiveData()
    private lateinit var retrofitService: retrofitService

    fun getMovies(context: Context){
        retrofitService = retrofitService()
        viewModelScope.launch(Dispatchers.IO){
            val call = retrofitService.service(context).create(
                moviesService::class.java).getMovies("now_playing")
            withContext(Dispatchers.Main){
                if(call.isSuccessful){
                    moviesResponse.value = call.body()
                }else{
                    moviesResponse.value = null
                }
            }
        }
    }


}