package org.tumo.MyTumo.service.attendance

import org.tumo.MyTumo.service.BaseNetworkService

class AttendanceService : BaseNetworkService() {
    suspend fun getStudentAttendance(limit: Int?): Result<List<StudentAttendance>> {
        val apiService = retrofit.create(AttendanceApi::class.java)
        return safeApiCall { apiService.studentAttendance(limit) }
    }
}