package org.tumo.MyTumo.service.attendance

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AttendanceApi {
    @GET("/students/attendance")
    suspend fun studentAttendance(@Query("limit") limit: Int?): Response<List<StudentAttendance>>
}