package com.jdev.jdevcompose.instagramapp.login.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("status") val success: String)