package org.tumo.MyTumo.service.extra

import org.tumo.MyTumo.service.BaseNetworkService

class ExtraService: BaseNetworkService() {

    suspend fun getStudentSchedule(limit: Int?): Result<List<StudentSchedule>> {
        val apiService = retrofit.create(ExtrasApi::class.java)
        return safeApiCall { apiService.studentSchedule(limit) }
    }
}