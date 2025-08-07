package org.tumo.MyTumo.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.tumo.MyTumo.MainActivity
import org.tumo.MyTumo.R
import org.tumo.MyTumo.service.login.TokenHolder
import org.tumo.MyTumo.ui.theme.ButtonYellow
import org.tumo.MyTumo.ui.theme.MyTumoTheme
import org.tumo.MyTumo.ui.theme.PurpleBox
import org.tumo.MyTumo.viewmodels.LoginViewModel

class LoginActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        if (!TokenHolder.token.isNullOrEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            setContent {
                MyTumoTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        LoginScreen()
                    }
                }
            }
        }

    }

    @Composable
    fun LoginScreen() {

        Column(
            modifier = Modifier
                .padding(12.dp, 50.dp, 12.dp, 12.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            LoginTitle()
            LoginFunctionalElements()
        }
        ShowLoginPopup()
        ShowErrorPopup()
    }

    @Composable
    private fun LoginFunctionalElements() {
        val usernameValue = remember { mutableStateOf(TextFieldValue()) }
        val passwordValue = remember { mutableStateOf(TextFieldValue()) }
        var passwordVisible by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(18.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(PurpleBox)
        )
        {
            Column(
                modifier = Modifier
                    .padding(20.dp, 40.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) {
                TextField(
                    value = usernameValue.value,
                    singleLine = true,
                    onValueChange = { textFieldValue: TextFieldValue ->
                        usernameValue.value = textFieldValue
                    },
                    placeholder = {
                        Text(getString(R.string.username))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
                TextField(
                    value = passwordValue.value,
                    singleLine = true,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    onValueChange = { textFieldValue: TextFieldValue ->
                        passwordValue.value = textFieldValue
                    },
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, "")
                        }
                    },
                    placeholder = {
                        Text(getString(R.string.password))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                )
            }
            LoginButton(usernameValue, passwordValue)
        }
    }

    @Composable
    private fun LoginButton(
        usernameValue: MutableState<TextFieldValue>,
        passwordValue: MutableState<TextFieldValue>
    ) {
        Button(
            onClick = {
                loginButtonClicked(
                    usernameValue.value.text,
                    passwordValue.value.text
                )
            },
            modifier = Modifier
                .padding(20.dp, 30.dp, 20.dp, 70.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(ButtonYellow)
        ) {
            Text(
                getString(R.string.enter_button), Modifier.padding(10.dp),
                style = TextStyle(fontWeight = FontWeight(700))
            )
        }
    }

    @Composable
    private fun LoginTitle() {
        Image(
            painterResource(id = R.drawable.tumo_icon),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(107.dp)
                .height(30.dp)
        )
        Text(
            text = getString(R.string.login_text),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(700),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(10.dp, 40.dp, 10.dp, 10.dp)
        )
    }

    @Composable
    private fun ShowErrorPopup() {

        val errorVisible = loginViewModel.liveDataError.observeAsState(initial = null)
        errorVisible.let {
            if (it.value != null) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(Color(0, 0, 0, 100))
                ) {
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .width(200.dp)
                            .background(Color.White)
                    ) {
                        Text(
                            text = getString(R.string.login_error),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth()
                        )
                        Button(
                            onClick = { loginViewModel.errorMessageShown() },
                            modifier = Modifier
                                .padding(10.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(PurpleBox)
                        ) {
                            Text(text = getString(R.string.ok_button), color = Color.White)
                        }
                    }

                }
            }
        }

    }

    @Composable
    private fun ShowLoginPopup() {

        val loginVisible = loginViewModel.liveDataLogin.observeAsState(initial = null)
        loginVisible.let {
            if (it.value != null) {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

    }


    private fun loginButtonClicked(usernameValue: String, passwordValue: String) {
        loginViewModel.authenticate(username = usernameValue, password = passwordValue)
    }


    @Preview(showBackground = true)
    @Composable
    fun ForPreview() {
        LoginScreen()
    }

}