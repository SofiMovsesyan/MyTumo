package org.tumo.MyTumo.ui.schedule

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import org.tumo.MyTumo.service.event.EventInfo
import org.tumo.MyTumo.viewmodels.EventViewModel


@Composable
fun ScheduleMainScreen() {
    val eventViewModel = viewModel<EventViewModel>()
    eventViewModel.loadEvents()
    val events = eventViewModel.liveDataEventInfo.observeAsState()
    events.let { eventsList ->
        eventsList.value?.let { list ->
            Schedules(list)
        }
    }
}

@Composable
fun Schedules(eventInfoList: List<EventInfo>) {
    // Create a mutable state to track the current screen
    val currentScreen = remember { mutableStateOf(Screen.EventSchedule) }

    var currentEventInfo: EventInfo? by remember { mutableStateOf(null) }
    // Based on the current screen state, display the corresponding view
    when (currentScreen.value) {
        Screen.EventSchedule -> EventSchedule(eventInfoList, onNavigateToMySchedule = {
            currentEventInfo = it
            currentScreen.value = Screen.MySchedule
        })

        Screen.MySchedule -> currentEventInfo?.let {
            MySchedule(it, onNavigateBack = {
                currentScreen.value = Screen.EventSchedule
            })
        }
    }
}

enum class Screen {
    EventSchedule,
    MySchedule
}
