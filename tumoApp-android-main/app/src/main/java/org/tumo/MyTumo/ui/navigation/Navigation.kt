package org.tumo.MyTumo.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.tumo.MyTumo.ui.navigation.components.NavigationContainer
import org.tumo.MyTumo.ui.profile.ProfileNavigation
import org.tumo.MyTumo.ui.schedule.ScheduleMainScreen
import org.tumo.MyTumo.ui.theme.MyTumoTheme
import org.tumo.MyTumo.ui.workshop.WorkshopMainScreen

enum class Route {
    Schedule,
    Workshops,
    Profile
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                NavigationContainer(navController)
            }
        }
    ) { PaddingValues ->
        NavHost(navController, startDestination = Route.Schedule.name) {
            composable(Route.Schedule.name) { ScheduleMainScreen() }
            composable(Route.Workshops.name) { WorkshopMainScreen() }
            composable(Route.Profile.name) { ProfileNavigation(PaddingValues) }
        }
    }
}

@Preview
@Composable
fun NavigationPreview() {
    MyTumoTheme(isDarkTheme = false) {
        MainScreen()
    }

}

@Preview
@Composable
fun NavigationPreviewDark() {
    MyTumoTheme(isDarkTheme = true) {
        MainScreen()
    }

}