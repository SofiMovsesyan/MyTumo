package org.tumo.MyTumo.ui.settings

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.tumo.MyTumo.R
import org.tumo.MyTumo.ui.components.MyTumoAppBar
import org.tumo.MyTumo.ui.login.LoginActivity
import org.tumo.MyTumo.ui.profile.ProfileRoutes
import org.tumo.MyTumo.viewmodels.LoginViewModel

@Composable
fun Settings(navController: NavController?) {
    val loginViewModel = viewModel<LoginViewModel>()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            MyTumoAppBar(
                content = stringResource(R.string.settings),
                showLeftIcon = true,
                onLeftIconPressed = { navController?.navigate(ProfileRoutes.Profile.name) })
        },
    ) { PaddingValues ->
        Button(
            onClick = {
                logout(loginViewModel, context)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingValues)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            shape = RoundedCornerShape(15.dp),
        ) {
            Text(
                text = "Logout", style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(800),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

fun logout(loginViewModel: LoginViewModel, context: Context) {
    loginViewModel.logout()
    context.startActivity(Intent(context, LoginActivity::class.java))
}
