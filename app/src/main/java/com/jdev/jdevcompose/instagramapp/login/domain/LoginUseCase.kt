package com.jdev.jdevcompose.instagramapp.login.domain

import com.jdev.jdevcompose.instagramapp.login.data.LoginRepository

class LoginUseCase {

    private val repository = LoginRepository()

    suspend operator fun invoke(user: String, password: String): String {
        return repository.doLogin(user, password)
    }
}