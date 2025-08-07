package org.tumo.MyTumo.service.event

import retrofit2.Response
import retrofit2.http.GET

interface EventApi {
    @GET("/events")
    suspend fun events(): Response<List<EventInfo>>
}