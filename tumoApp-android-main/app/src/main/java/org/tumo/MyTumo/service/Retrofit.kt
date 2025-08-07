package org.tumo.MyTumo.service

import org.tumo.MyTumo.service.ApiConstants.BASE_URL_MY_TUMO
import org.tumo.MyTumo.service.ApiConstants.BASE_URL_T360
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.tumo.MyTumo.service.login.TokenHolder


object RetrofitHelper {

    fun getInstance(type: RetrofitType = RetrofitType.DEFAULT): Retrofit {
        val baseUrl = when (type) {
            RetrofitType.DEFAULT, RetrofitType.AUTH -> BASE_URL_MY_TUMO
            RetrofitType.T360 -> BASE_URL_T360
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .apply {
                if (type == RetrofitType.DEFAULT || type == RetrofitType.T360) {
                    client(OkHttpClient.Builder().addInterceptor(TokenInterceptor()).build())
                }
            }
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val token = TokenHolder.token
        return if (token != null) {
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        } else {
            chain.proceed(originalRequest)
        }
    }
}


enum class RetrofitType {
    DEFAULT,
    AUTH,
    T360
}
