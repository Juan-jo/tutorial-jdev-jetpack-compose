package com.jdev.jdevcompose.instagramapp.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginInstagramViewModel: ViewModel() {

    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _isLoginEnable = MutableLiveData<Boolean>()

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enabledLogin(email, password)
    }

    private fun enabledLogin(email: String, password: String): Boolean {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.length > 6 )
    }

}