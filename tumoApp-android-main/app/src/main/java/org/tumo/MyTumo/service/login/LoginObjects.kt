package org.tumo.MyTumo.service.login

import com.google.gson.annotations.SerializedName

data class LoginBody(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)

data class LoginResponse(
    @SerializedName("token")
    val token: String
)

