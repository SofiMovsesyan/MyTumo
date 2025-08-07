package org.tumo.MyTumo.service.WorkshopFilter

import retrofit2.Response
import retrofit2.http.GET

interface SkillsApi {
    @GET("/configs/skill")
    suspend fun getSkills(): Response<List<WorkshopSkill>>
}