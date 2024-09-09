package com.jdev.jdevcompose.instagramapp.login.data.network

import com.jdev.jdevcompose.instagramapp.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {

    @GET("/api/breeds/image/random")
    suspend fun doLogin(): Response<LoginResponse>
}