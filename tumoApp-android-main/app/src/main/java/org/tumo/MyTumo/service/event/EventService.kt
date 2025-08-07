package org.tumo.MyTumo.service.event

import org.tumo.MyTumo.service.BaseNetworkService

class EventService : BaseNetworkService() {
    suspend fun loadEvents(): Result<List<EventInfo>> {
        val apiService = retrofit.create(EventApi::class.java)
        return safeApiCall { apiService.events() }
    }
}
