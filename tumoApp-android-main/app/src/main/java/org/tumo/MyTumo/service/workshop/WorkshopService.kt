package org.tumo.MyTumo.service.workshop

import org.tumo.MyTumo.service.BaseNetworkService

class WorkshopService : BaseNetworkService() {
    suspend fun getWorkshopInfo(): Result<List<WorkshopInfo>> {
        val apiService = retrofit.create(WorkshopApi::class.java)
        return safeApiCall { apiService.workshopList() }
    }

    suspend fun getWorkshopDetails(id: String): Result<WorkshopDetails> {
        val apiService = retrofit.create(WorkshopApi::class.java)
        return safeApiCall { apiService.workshopDetails(id) }
    }
}