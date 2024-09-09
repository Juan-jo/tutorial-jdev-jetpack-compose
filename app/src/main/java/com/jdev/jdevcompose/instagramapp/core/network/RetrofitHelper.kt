package com.jdev.jdevcompose.instagramapp.core.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/")//://dog.ceo/api/breeds/image/random
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}