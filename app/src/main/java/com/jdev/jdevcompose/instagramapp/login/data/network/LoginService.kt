package com.jdev.jdevcompose.instagramapp.login.data.network

import com.jdev.jdevcompose.instagramapp.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doLogin(user: String, password: String): String {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(LoginClient::class.java).doLogin()

            response.body()?.success ?: ""
        }
    }
}