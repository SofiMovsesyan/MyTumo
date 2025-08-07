package org.tumo.MyTumo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tumo.MyTumo.service.student.StudentInfo
import org.tumo.MyTumo.service.student.StudentService

class StudentViewModel : BaseViewModel() {
    private val _liveDataStudentInfo = MutableLiveData<StudentInfo>()
    val liveDataStudentInfo: LiveData<StudentInfo> = _liveDataStudentInfo

    val studentService by lazy { StudentService() }

    fun getStudentInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = studentService.getStudentInfo()
            handleResult(result, _liveDataStudentInfo)

        }
    }

}