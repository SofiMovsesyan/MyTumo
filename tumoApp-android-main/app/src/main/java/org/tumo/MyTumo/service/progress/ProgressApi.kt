package org.tumo.MyTumo.service.progress

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProgressApi {
    @GET("/students/progress")
    suspend fun studentProgress(@Query("limit") limit: Int?): Response<StudentProgress>
}