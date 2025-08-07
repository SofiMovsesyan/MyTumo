package org.tumo.MyTumo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected val _liveDataError: MutableLiveData<String> = MutableLiveData<String>()
    val liveDataError: LiveData<String> = _liveDataError

    fun <T> handleResult(result: Result<T>, liveData: MutableLiveData<T>) {
        result.onSuccess { data ->
            liveData.postValue(data)
        }.onFailure { exception ->
            Log.d("BaseViewModel", "handleResult:${exception.message} ")
            _liveDataError.postValue(exception.message)
        }
    }

    fun <T> checkError(result: Result<T>, success: (result: T) -> Unit) {
        result.onSuccess {
            success(it)
        }.onFailure { exception ->
            Log.d("BaseViewModel", "handleResult:${exception.message} ")
            _liveDataError.postValue(exception.message)
        }
    }


}