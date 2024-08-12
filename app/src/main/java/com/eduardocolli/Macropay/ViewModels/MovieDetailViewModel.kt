package com.eduardocolli.Macropay.ViewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduardocolli.Macropay.Models.Genres
import com.eduardocolli.Macropay.Models.detailResponse
import com.eduardocolli.Macropay.Services.moviesService
import com.eduardocolli.Macropay.Services.retrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel: ViewModel() {

    var genresResponse: MutableLiveData<detailResponse> = MutableLiveData()
    private lateinit var retrofitService: retrofitService

    fun getGenres(context: Context, id: String){
        retrofitService = retrofitService()
        println("el id es" + id)
        viewModelScope.launch(Dispatchers.IO){
            val call = retrofitService.service(context).create(
                moviesService::class.java).getGenres(id)
            withContext(Dispatchers.Main){
                println(call)
                if(call.isSuccessful){
                    Log.d("tagx2", call.body().toString())
                    genresResponse.value = call.body()
                }
            }
        }
    }
}