package org.tumo.MyTumo.service.extra

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExtrasApi {
    @GET("/schedule")
    suspend fun studentSchedule(@Query("limit") limit: Int?): Response<List<StudentSchedule>>
}