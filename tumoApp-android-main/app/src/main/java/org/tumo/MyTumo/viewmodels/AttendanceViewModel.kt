package org.tumo.MyTumo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tumo.MyTumo.service.attendance.AttendanceService
import org.tumo.MyTumo.service.attendance.StudentAttendance

class AttendanceViewModel  : BaseViewModel() {
    private val _liveDataStudentAttendance = MutableLiveData<List<StudentAttendance>>()
    val liveDataStudentAttendance: LiveData<List<StudentAttendance>> = _liveDataStudentAttendance

    private val studentAttendance by lazy { AttendanceService() }

    fun getStudentAttendance(limit: Int? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = studentAttendance.getStudentAttendance(limit)
            handleResult(result, _liveDataStudentAttendance)
        }
    }
}