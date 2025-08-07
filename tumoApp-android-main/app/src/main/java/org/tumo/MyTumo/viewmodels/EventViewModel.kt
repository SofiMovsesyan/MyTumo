package org.tumo.MyTumo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tumo.MyTumo.service.event.EventInfo
import org.tumo.MyTumo.service.event.EventService

class EventViewModel : BaseViewModel() {
    private val _liveDataEventInfo = MutableLiveData<List<EventInfo>>()
    val liveDataEventInfo: LiveData<List<EventInfo>> = _liveDataEventInfo

    val eventService by lazy { EventService() }

    fun loadEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = eventService.loadEvents()
            Log.d("tag",result.toString())
            handleResult(result, _liveDataEventInfo)
        }
    }
}