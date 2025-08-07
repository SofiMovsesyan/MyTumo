package org.tumo.MyTumo.ui.workshop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun WorkshopMainScreen() {
    // Create a mutable state to track the current screen
    val currentScreen = remember { mutableStateOf(WorkshopScreen.WorkshopsScreen) }

    var currentWorkshop: String? by remember { mutableStateOf(null) }
    // Based on the current screen state, display the corresponding view
    when (currentScreen.value) {
        WorkshopScreen.WorkshopsScreen -> WorkshopsScreen {
            currentWorkshop = it
            currentScreen.value = WorkshopScreen.WorkshopSecondPage
        }

        WorkshopScreen.WorkshopSecondPage -> currentWorkshop?.let {
            WorkshopDetails(it) {
                currentScreen.value = WorkshopScreen.WorkshopsScreen
            }
        }
    }
}

enum class WorkshopScreen {
    WorkshopsScreen,
    WorkshopSecondPage
}