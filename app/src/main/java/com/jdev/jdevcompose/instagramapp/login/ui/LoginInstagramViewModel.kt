package com.jdev.jdevcompose.instagramapp.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdev.jdevcompose.instagramapp.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginInstagramViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase) : ViewModel() {


    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _isLoginEnable = MutableLiveData<Boolean>()
    private val _isLoading = MutableLiveData<Boolean>()

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enabledLogin(email, password)
    }

    fun onLoginSelected() {

        viewModelScope.launch {

            _isLoading.value = true

            val result = loginUseCase(
                user = email.value!!,
                password = password.value!!
            )

            if (result != "") {
                Log.i("delicias", "resultOk")
            } else {
                Log.i("delicias", "No - Ok :/")
            }

            _isLoading.value = false

        }

    }

    private fun enabledLogin(email: String, password: String): Boolean {
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.length > 6)
    }

}