package com.eduardocolli.Macropay.Services

import android.content.Context
import com.eduardocolli.Macropay.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.coroutineContext

class retrofitService {

    fun service(context: Context): Retrofit{
        return Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}