package org.tumo.MyTumo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tumo.MyTumo.service.extra.ExtraService
import org.tumo.MyTumo.service.extra.StudentSchedule

class ExtraViewModel : BaseViewModel() {

        private val _liveDataStudentSchedule = MutableLiveData<List<StudentSchedule>>()
        val liveDataStudentSchedule: LiveData<List<StudentSchedule>> = _liveDataStudentSchedule

        private val studentExtra by lazy { ExtraService() }

        fun getStudentExtra(limit: Int? = null) {
            viewModelScope.launch(Dispatchers.IO) {
                val result = studentExtra.getStudentSchedule(limit = limit)
                handleResult(result, _liveDataStudentSchedule)
            }
        }
}