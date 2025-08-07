package org.tumo.MyTumo.service.WorkshopFilter

import org.tumo.MyTumo.service.BaseNetworkService


class SkillsService : BaseNetworkService() {
    suspend fun getSkills(): Result<List<WorkshopSkill>> {
        val apiSkillService = retrofit.create(SkillsApi::class.java)
        return safeApiCall { apiSkillService.getSkills() }
    }
}
