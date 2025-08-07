package org.tumo.MyTumo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.tumo.MyTumo.ui.navigation.MainScreen
import org.tumo.MyTumo.ui.theme.MyTumoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTumoTheme {
                MainScreen()
            }
        }
    }
}