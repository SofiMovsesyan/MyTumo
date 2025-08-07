package org.tumo.MyTumo.ui.schedule

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import org.tumo.MyTumo.R
import org.tumo.MyTumo.service.event.EventInfo
import org.tumo.MyTumo.ui.theme.*


@Composable
fun MySchedule(eventInfo: EventInfo, onNavigateBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(enabled = true, state = ScrollState(0))
            .background(BackgroudGray),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        WorkshopDetailsNavigationBar(onNavigateBack)
        EventColumn(eventInfo = eventInfo)
        WorkshopDetailsDeescription(eventInfo)

        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier
                .padding(22.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Purple200)
        ) {
            Text(text = stringResource(id = R.string.schedule_hours_button), color = Color.White)
        }
        WorkshopLine()
        WorkshopPrerequisite(eventInfo)
        Spacer(modifier = Modifier.height(90.dp))
    }
}

@Composable
private fun WorkshopPrerequisite(eventInfo: EventInfo) {
    eventInfo.prerequisites?.let {
        if (it.events?.item != null) {
            WorkshopPrerequisiteButtons()
            WorkshopPrerequisiteDetails(eventInfo = eventInfo)
        }
    }
}

@Preview
@Composable
private fun WorkshopPrerequisiteButtons() {
    Text(
        text = stringResource(id = R.string.prerequisite_title),
        style = TextStyle(
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 17.sp,
            color = Color.Black
        ),
        modifier = Modifier
            .padding(start = 22.dp, top = 15.dp, bottom = 1.dp)
    )

    val borderWidth = 1.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = borderWidth)
                .size(width = 160.dp, height = 36.dp)
        ) {
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .fillMaxSize(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.exercise_button),
                    textAlign = TextAlign.Center
                )
            }
            Box(
                Modifier
                    .border(
                        width = 2.dp,
                        color = ExerciseButton,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(end = borderWidth)
                .size(width = 160.dp, height = 36.dp)
        ) {
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .fillMaxSize(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = GreyBackground,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.workshop),
                    textAlign = TextAlign.Center
                )
            }
            Box(
                Modifier
                    .border(
                        width = 2.dp,
                        color = ExerciseButton,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .fillMaxSize()
            )
        }

    }
}

@Composable
private fun WorkshopDetailsDeescription(eventInfo: EventInfo) {
    eventInfo.description.armenian.let { it ->
        if (it.isNotBlank()) {
            Description(description = it)
        }
    }
}

@Composable
private fun WorkshopDetailsNavigationBar(onNavigateBack: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onNavigateBack,
        ) {
            Image(
                painter = painterResource(R.drawable.back_icon),
                contentDescription = "Nkar Icon",
                modifier = Modifier
                    .width(32.dp)
                    .heightIn(26.dp)
            )
        }
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Title(stringResource(R.string.my_schedule_title), color = Purple200)
        }
    }
}


@Composable
fun WorkshopLine() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = 10.dp)
            .padding(vertical = 10.dp)
    ) {
        drawLine(
            color = LightGrayVariant,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            strokeWidth = 1.dp.toPx(),

            )
    }

}

@Composable
fun Title(name: String, color: Color) {
    Text(
        text = name,
        color = color,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        ),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    )
}

@Composable
fun Description(description: String) {
    LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp, start = 24.dp, end = 24.dp)
            .wrapContentSize(Alignment.TopCenter)
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx: Context ->
                WebView(ctx).apply {
                    webViewClient = WebViewClient()
                    loadData(description, "text/html", "UTF-8")
                    setBackgroundColor(BackgroudGray.toArgb())
                }
            }
        )
    }
}


@Composable
fun WorkshopPrerequisiteDetails(eventInfo: EventInfo) {
    val skill = Skill.values().find { it.nameKey == eventInfo.skill }
    skill?.let { Skill.getColorByNameKey(it.nameKey) } ?: Color.Gray
    val prerequisites = eventInfo.prerequisites
    val actItem = prerequisites?.activities?.item
    val eventItem = prerequisites?.events?.item
    eventInfo.title.armenian
    skill?.imageResId ?: R.drawable.event_icon
    eventInfo.getFormattedStartDate()
    eventInfo.getFormattedEndDate()

    val actIcon = actItem?.icon
    val eventIcon = eventItem?.let { Skill.getImageResIdByNameKey(it.skill) }
    val preText = actItem?.skill?.let { Skill.getTextByNameKey(it) }
    val preTitle = actItem?.title?.armenian

    Button(
        onClick = { /* Handle button click */ },
        modifier = Modifier
            .padding(22.dp)
            .fillMaxWidth()
            .height(83.dp) // Adjust the height value as desired
            .width(338.dp) // Adjust the width value as desired
            .clip(RoundedCornerShape(5.dp))
            .border(1.dp, Color.Black, shape = RoundedCornerShape(5.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (actIcon != null) {
                Image(
                    painter = rememberAsyncImagePainter(actIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .width(47.dp)
                        .height(46.dp)
                        .align(Alignment.CenterVertically),
                    contentScale = ContentScale.Fit
                )
            } else if (eventIcon != null) {
                Image(
                    painterResource(id = eventIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .width(47.dp)
                        .height(46.dp)
                        .align(Alignment.CenterVertically),
                    contentScale = ContentScale.Fit
                )
            } else {
                Image(
                    painterResource(id = R.drawable.ellipse),
                    contentDescription = null,
                    modifier = Modifier
                        .width(47.dp)
                        .height(46.dp)
                        .align(Alignment.CenterVertically),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(preText.toString(), color = Color.Black, modifier = Modifier.fillMaxWidth())
                Text(preTitle.toString(), color = Color.Gray, modifier = Modifier.fillMaxWidth())
            }
        }
    }

}

