package org.tumo.MyTumo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tumo.MyTumo.service.progress.ProgressService
import org.tumo.MyTumo.service.progress.StudentProgress

class ProgressViewModel : BaseViewModel() {
    private val _liveDataStudentProgress = MutableLiveData<StudentProgress>()
    val liveDataStudentProgress: LiveData<StudentProgress> = _liveDataStudentProgress

    private val progressService by lazy { ProgressService() }

    fun getStudentProgress(limit: Int? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = progressService.getStudentProgress(limit)
            handleResult(result, _liveDataStudentProgress)

        }
    }

}