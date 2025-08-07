package org.tumo.MyTumo.service.progress

import org.tumo.MyTumo.service.BaseNetworkService

class ProgressService : BaseNetworkService() {
    suspend fun getStudentProgress(limit: Int?): Result<StudentProgress> {
        val apiService = retrofit.create(ProgressApi::class.java)
        return safeApiCall { apiService.studentProgress(limit) }
    }
}