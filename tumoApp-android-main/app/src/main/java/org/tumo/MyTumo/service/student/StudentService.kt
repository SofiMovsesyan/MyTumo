package org.tumo.MyTumo.service.student

import org.tumo.MyTumo.service.BaseNetworkService

class StudentService : BaseNetworkService() {
    suspend fun getStudentInfo(): Result<StudentInfo> {
        val apiService = retrofit.create(StudentApi::class.java)
        return safeApiCall { apiService.studentInfo() }
    }
}