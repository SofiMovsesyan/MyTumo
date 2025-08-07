package org.tumo.MyTumo.service.student

import retrofit2.Response
import retrofit2.http.GET

interface StudentApi {
    @GET("/students/info")
    suspend fun studentInfo(): Response<StudentInfo>
}