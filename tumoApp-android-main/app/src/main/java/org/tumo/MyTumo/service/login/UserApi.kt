package org.tumo.MyTumo.service.login

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("auth/login")
    suspend fun authenticate(@Body loginInfo: LoginBody): Response<LoginResponse>
}