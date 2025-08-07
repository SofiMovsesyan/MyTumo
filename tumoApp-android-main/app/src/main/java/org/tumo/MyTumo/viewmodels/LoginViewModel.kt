package org.tumo.MyTumo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tumo.MyTumo.service.login.LoginResponse
import org.tumo.MyTumo.service.login.LoginService

class LoginViewModel : BaseViewModel() {
    private val _liveDataLogin: MutableLiveData<LoginResponse> = MutableLiveData<LoginResponse>()
    val liveDataLogin: LiveData<LoginResponse> = _liveDataLogin

    private val loginService: LoginService by lazy { LoginService() }

    fun authenticate(username: String, password: String) {
        Log.i("asd", "authenticate: ")
        viewModelScope.launch(Dispatchers.IO) {
            val result = loginService.authenticate(username, password)
            handleResult(result, _liveDataLogin)
        }
    }

    fun errorMessageShown() {
        _liveDataError.postValue(null)
    }

    fun logout() {
        viewModelScope.launch { loginService.logout() }
    }
}