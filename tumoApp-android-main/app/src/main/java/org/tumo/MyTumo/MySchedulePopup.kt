package org.tumo.MyTumo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.tumo.MyTumo.ui.theme.MyTumoTheme
import org.tumo.MyTumo.ui.theme.PurpleBox

class MySchedulePopup : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTumoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {
                    DefaultPreview()
                }
            }
        }

    }

    @Preview
    @Composable
    fun ForPreview() {
        DefaultPreview()
    }

    @Composable
    fun DefaultPreview() {
        var showPopup by remember { mutableStateOf(true) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Button(onClick = { showPopup = !showPopup }) {
                Text("Click me!")
            }
        }
        if (showPopup) {
            Dialog(
                onDismissRequest = { showPopup = false }
            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .padding(15.dp, 25.dp, 15.dp, 125.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .height(60.dp)
                            .background(PurpleBox),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painterResource(id = R.drawable.watch),
                            contentDescription = "",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                        )
                    }
                    Text(
                        "Schedule",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            fontWeight = FontWeight(750)
                        ),
                        modifier = Modifier
                            .padding(5.dp)
                    )
                    Column() {
                        Text(
                            "1. Aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            )
                        )
                        Text(
                            "2. Aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            ),
                            maxLines = 1
                        )
                        Text(
                            "3. Aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            )
                        )
                        Text(
                            "4. Aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            )
                        )
                        Text(
                            "5. Aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            )
                        )
                        Text(
                            "6. Aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            )
                        )
                        Text(
                            "7. Aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            )
                        )
                        Text(
                            "8. Aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                            style = TextStyle(
                                textAlign = TextAlign.Center,
                                fontSize = 15.sp
                            )
                        )
                    }
                }
            }
        }
    }
}