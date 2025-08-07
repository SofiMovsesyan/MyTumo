package org.tumo.MyTumo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tumo.MyTumo.service.WorkshopFilter.SkillsService
import org.tumo.MyTumo.service.WorkshopFilter.WorkshopSkill
import org.tumo.MyTumo.service.workshop.WorkshopDetails
import org.tumo.MyTumo.service.workshop.WorkshopInfo
import org.tumo.MyTumo.service.workshop.WorkshopService

class WorkshopViewModel : BaseViewModel() {
    private val _liveDataSkillsInfo = MutableLiveData<List<WorkshopSkill>>()
    private val _liveDataWorkshopsInfo = MutableLiveData<List<WorkshopInfo>>()
    private val _liveDataWorkshopDetails = MutableLiveData<WorkshopDetails>()

    private var fullList = mutableSetOf<WorkshopInfo>()
    private val filters = mutableListOf<WorkshopSkill>()

    val liveDataSkillsInfo: LiveData<List<WorkshopSkill>> = _liveDataSkillsInfo
    val liveDataWorkshopsInfo: LiveData<List<WorkshopInfo>> = _liveDataWorkshopsInfo
    val liveDataWorkshopDetails: LiveData<WorkshopDetails> = _liveDataWorkshopDetails

    private val workshopService by lazy { WorkshopService() }
    private val skillsService by lazy { SkillsService() }

    fun getSkills() {
        if (liveDataSkillsInfo.value?.isNotEmpty() == true) {
            _liveDataSkillsInfo.postValue(liveDataSkillsInfo.value)
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                val skills = skillsService.getSkills()
                handleResult(skills, _liveDataSkillsInfo)
            }
        }
    }

    fun getWorkshopInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = workshopService.getWorkshopInfo()
            if (result.isSuccess) {
                result.getOrNull()?.let {
                    fullList.addAll(it)
                }
            }
            handleResult(result, _liveDataWorkshopsInfo)
        }
    }

    fun getWorkshopDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = workshopService.getWorkshopDetails(id)
            handleResult(result, _liveDataWorkshopDetails)
        }
    }

    fun addOrRemoveFilter(skill: WorkshopSkill) {
        skill.selected = !skill.selected

        viewModelScope.launch(Dispatchers.IO) {
            if (filters.contains(skill)) {
                filters.remove(skill)
            } else {
                filters.add(skill)
            }
            filterWorkshops()
        }
    }

    private fun filterWorkshops() {

        if (filters.isEmpty()) {
            _liveDataWorkshopsInfo.postValue(fullList.toList())
        } else {
            val filteredWorkshops = fullList.filter { workshop ->
                filters.any { filter -> filter.name == workshop.skill }
            }
            _liveDataWorkshopsInfo.postValue(filteredWorkshops)
        }
    }

}