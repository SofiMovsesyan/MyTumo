package org.tumo.MyTumo.service

import retrofit2.Response

open class BaseNetworkService {
    open val retrofit by lazy { RetrofitHelper.getInstance() }

    suspend fun <T> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        return runCatching {
            val response = call()
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Server response is null")
            } else {
                throw Exception("Server error: ${response.message()}")
            }
        }
    }
}
