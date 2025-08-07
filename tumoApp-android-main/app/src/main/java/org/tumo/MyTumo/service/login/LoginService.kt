package org.tumo.MyTumo.service.login

import org.tumo.MyTumo.service.BaseNetworkService
import org.tumo.MyTumo.service.RetrofitHelper
import org.tumo.MyTumo.service.RetrofitType
import retrofit2.Retrofit

class LoginService : BaseNetworkService() {
    override val retrofit: Retrofit
        get() = RetrofitHelper.getInstance(RetrofitType.AUTH)

    suspend fun authenticate(userName: String, password: String): Result<LoginResponse> {
        val apiService = retrofit.create(UserApi::class.java)
        val result = safeApiCall { apiService.authenticate(LoginBody(userName, password)) }
        if (result.isSuccess) {
            TokenHolder.token = result.getOrNull()?.token
        }
        return result
    }

    fun logout() {
        TokenHolder.token = null
    }
}