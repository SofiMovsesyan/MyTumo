package org.tumo.MyTumo.ui.navigation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.tumo.MyTumo.R
import org.tumo.MyTumo.ui.navigation.Route

@Composable
fun NavigationContainer(navController: NavHostController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(15.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            BottomBarItem(
                iconResId = R.drawable.ic_menu_home,
                contentDescription = "Home",
                isSelected = currentDestination == Route.Schedule.name,
                onClick = {
                    navController.navigate(Route.Schedule.name)
//                    screen.value = Route.Home
                }
            )
            BottomBarItem(
                iconResId = R.drawable.ic_menu_workshops,
                contentDescription = "Workshops",
                isSelected = currentDestination == Route.Workshops.name,
                onClick = {
                    navController.navigate(Route.Workshops.name)
//                    screen.value = Route.Workshops
                }
            )
            BottomBarItem(
                iconResId = R.drawable.ic_menu_profile,
                contentDescription = "Profile",
                isSelected = currentDestination == Route.Profile.name,
                onClick = {
                    navController.navigate(Route.Profile.name)
//                    screen.value = Route.Profile
                }
            )
        }
    }
}


@Composable
fun BottomBarItem(
    iconResId: Int,
    contentDescription: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val tint = MaterialTheme.colorScheme.onSurfaceVariant
    Image(
        painter = painterResource(iconResId),
        contentDescription = contentDescription,
        colorFilter = if (isSelected) ColorFilter.tint(tint) else null,
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .fillMaxWidth()
            .padding(5.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .padding(10.dp)
    )

}