package com.jdev.jdevcompose.instagramapp.login.data

import com.jdev.jdevcompose.instagramapp.login.data.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val api: LoginService
) {

    suspend fun doLogin(user: String, password: String): String {
        return api.doLogin(user, password)
    }
}