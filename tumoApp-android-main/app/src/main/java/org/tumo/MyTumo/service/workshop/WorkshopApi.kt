package org.tumo.MyTumo.service.workshop

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WorkshopApi {
    @GET("events/online")
    suspend fun workshopList(): Response<List<WorkshopInfo>>

    @GET("events/online/{id}")
    suspend fun workshopDetails(@Path("id") id: String): Response<WorkshopDetails>
}