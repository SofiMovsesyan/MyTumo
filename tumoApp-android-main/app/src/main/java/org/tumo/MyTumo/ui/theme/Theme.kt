package org.tumo.MyTumo.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val TumoLightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    primaryContainer = PrimaryVariant,
    onPrimaryContainer = Color.White,
    secondary = DarkBlue40,
    onSecondary = Color.White,
    tertiary = Yellow40,
    onTertiary = Color.White,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = LightGray,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = LightGrayVariant,
    inverseSurface = Grey20,
    inverseOnSurface = Grey95,
    surfaceVariant = PrimaryVariant,
    onSurfaceVariant = Color.White,
    outline = LightGrayVariant
)

private val TumoDarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    primaryContainer = PrimaryVariant,
    onPrimaryContainer = Color.White,
    secondary = DarkBlue40,
    onSecondary = Color.White,
    tertiary = Yellow40,
    onTertiary = Color.White,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Grey20.copy(alpha = 0.9f),
    onBackground = Color.White,
    surface = LightGrayVariant.copy(alpha = 0.9f),
    onSurface = Grey80.copy(alpha = 0.9f),
    inverseSurface = Grey95.copy(alpha = 0.9f),
    inverseOnSurface = Grey20.copy(alpha = 0.9f),
    surfaceVariant = PrimaryVariant.copy(alpha = 0.9f),
    onSurfaceVariant = Color.White,
    outline = LightGrayVariant.copy(alpha = 0.9f)
)




@SuppressLint("NewApi")
@Composable
fun MyTumoTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val myColorScheme = when {
        isDynamicColor && isDarkTheme -> {
            dynamicDarkColorScheme(LocalContext.current)
        }
        isDynamicColor && !isDarkTheme -> {
            dynamicLightColorScheme(LocalContext.current)
        }
        isDarkTheme -> TumoDarkColorScheme
        else -> TumoLightColorScheme
    }

    MaterialTheme(
        colorScheme = myColorScheme,
        typography = Typography,
        content = content
    )
}
