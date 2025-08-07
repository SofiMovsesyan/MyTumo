package org.tumo.MyTumo.ui.workshop

import android.content.Context
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import org.tumo.MyTumo.R
import org.tumo.MyTumo.ui.theme.GreyBackground
import org.tumo.MyTumo.ui.theme.LightGrayVariant
import org.tumo.MyTumo.ui.theme.Purple200
import org.tumo.MyTumo.viewmodels.WorkshopViewModel


private fun getWorkshopByIdInfo(workshopViewModel: WorkshopViewModel, id: String) {
    workshopViewModel.getWorkshopDetails(id)
}

@Composable
fun WorkshopDetails(workshopId: String, onNavigateBack: () -> Unit) {
    val workshopViewModel = viewModel<WorkshopViewModel>()
    getWorkshopByIdInfo(workshopViewModel, workshopId)
    val workshopById by workshopViewModel.liveDataWorkshopDetails.observeAsState()
    workshopById?.let { byIdObjects ->

        val skill = Skill.values().find { it.nameKey == byIdObjects.skill }
        val color = skill?.let { Skill.getColorByNameKey(it.nameKey) } ?: Color.Gray
        val startDateFormattedByHourAndMinute = byIdObjects.startDateFormattedByHourAndMinute
        val endDateFormattedByHourAndMinute = byIdObjects.endDateFormattedByHourAndMinute
        WorkshopMain(
            byIdObjects.startDateFormatted,
            byIdObjects.endDateFormatted,
            byIdObjects.description.english,
            byIdObjects.eventHosts.get(0).photoUrl,
            byIdObjects.eventHosts.get(0).name.english,
            byIdObjects.title.english,
            "$startDateFormattedByHourAndMinute-$endDateFormattedByHourAndMinute",
            byIdObjects.startDateFormattedByDayOfWeek,
            color,
            byIdObjects.dependencies.get(0).title.english,
            onNavigateBack
        )
        Log.d("TAG", byIdObjects.startDate.toString())
    }
}


@Composable
fun WorkshopBackIcon(onNavigateBack: () -> Unit) {
    Image(
        painter = painterResource(R.drawable.back_icon),
        contentDescription = "My Image",
        modifier = Modifier
            .size(65.dp)
            .padding(top = 29.dp, start = 24.dp)
            .clickable {
                onNavigateBack()
            }
    )
}

@Composable
fun WorkshopHeader() {
    Text(
        text = stringResource(id = R.string.learning_lab),
        modifier = Modifier
            .padding(top = 30.dp, bottom = 36.dp, end = 56.dp)
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight(800),
            color = Purple200,
            textAlign = TextAlign.Center
        )

    )


}

@Composable
fun WorkshopDateText(startDate: String, endDate: String) {
    Modifier
        .offset(x = 24.dp, y = 376.dp)
        .width(110.dp)
        .height(16.dp)
    Text(
        modifier = Modifier.padding(
            top = 48.dp,
            start = 24.dp,
            bottom = 29.dp
        ),
        text = "$startDate  $endDate",
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
        )
    )

}

@Composable
fun WorkshopMainText(description: String) {
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
                    setBackgroundColor(GreyBackground.toArgb())
                }
            },
            update = { webView ->
                webView.loadDataWithBaseURL(null, description, "text/html", "UTF-8", null)
            }
        )
    }
}

@Composable
fun WorkshopPrerequisite(prerequisite: String) {
    Modifier
        .offset(x = 24.dp, y = 376.dp)
        .width(110.dp)
        .height(16.dp)
    Text(
        modifier = Modifier.padding(
            top = 48.dp,
            start = 24.dp,
            bottom = 29.dp
        ),
        text = "Prerequisite $prerequisite",
        fontWeight = FontWeight.Bold,
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight(500),
            textAlign = TextAlign.Center,
        )
    )

}


@Composable
fun WorkshopChooseDateText() {
    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopCenter)
    ) {
        Modifier
            .offset(x = 76.dp, y = 915.dp)
            .width(243.dp)
            .height(16.dp)
        Text(
            modifier = Modifier.padding(
                top = 33.dp,
                bottom = 20.dp,
            ),
            text = stringResource(id = R.string.choose_time),
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(700),
            )
        )
    }

}

@Composable
fun WorkshopLine() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = 25.dp)
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
fun WorkshopChooseDateBox(
    startDate: String,
    workshopHoursAndMinutes: String,
    workshopDayOfWeek: String
) {
    Box(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, bottom = 95.dp)
            .width(165.dp)
            .height(66.dp)
            .background(
                color = Purple200,
                shape = RoundedCornerShape(size = 10.dp)
            )
    ) {
        Modifier
            .offset(x = 17.dp, y = 18.dp)
            .width(64.dp)
            .height(15.dp)
        Row(modifier = Modifier.padding(top = 9.dp, start = 9.dp)) {
            Text(
                text = "Start ",
                style = TextStyle(
                    fontSize = 11.sp,
                    fontWeight = FontWeight(400),
                    color = Color.White
                )
            )
            Text(
                text = startDate,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    fontSize = 11.sp,
                    fontWeight = FontWeight(400),
                    color = Color.White
                )
            )
        }

        Row(modifier = Modifier.padding(top = 25.dp, start = 9.dp)) {
            Text(

                text = "$workshopDayOfWeek ",
                style = TextStyle(
                    fontSize = 11.sp,
                    fontWeight = FontWeight(400),
                    color = Color.White
                )
            )
            Text(
                text = workshopHoursAndMinutes,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    fontSize = 11.sp,
                    fontWeight = FontWeight(400),
                    color = Color.White
                )
            )
        }


    }
}

@Composable
fun WorkshopPreTitle(name: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
            .padding(8.dp)
    ) {
        Modifier
            .offset(x = 142.dp, y = 16.dp)
            .width(75.dp)
            .height(14.dp)
        Text(
            text = name,

            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(700),
                color = LightGrayVariant,
                textAlign = TextAlign.Center,

                )
        )
    }
}


@Composable
fun WorkshopInfoBox(url: String, name: String, workshopName: String, skillColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .offset(x = 0.dp, y = 0.dp)
            .width(342.dp)
            .height(240.dp)
            .background(color = Color.White, shape = RoundedCornerShape(size = 10.dp))

    ) {
        WorkshopPreTitle(name = stringResource(id = R.string.learning_lab))
        WorkshopLeaderIcon(url, skillColor)
        WorkshopLeaderName(name, skillColor)
        WorkshopInfoBottomBox(workshopName, skillColor)
    }
}


@Composable
fun WorkshopInfoBottomBox(workshopName: String, skillColor: Color) {
    Column(
        Modifier
            .offset(x = 0.dp, y = 171.dp)
            .width(343.dp)
            .height(77.dp)
            .background(
                color = skillColor,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 10.dp
                )
            )
            .padding(start = 5.dp, top = 6.dp, end = 135.dp, bottom = 43.dp)
    )
    {
        Text(
            modifier = Modifier
                .offset(x = 11.dp, y = 6.dp)
                .width(197.dp)
                .height(28.dp),
            text = workshopName,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(400),
                color = Color.White,
            )
        )

    }
}

@Composable
fun WorkshopLeaderName(name: String, color: Color) {

    Text(
        text = name,
        style = TextStyle(
            fontSize = 10.sp,
        ),
        fontWeight = FontWeight(700),
        color = color,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .offset(x = 24.dp, y = 128.dp)
            .width(87.dp)
            .height(28.dp)
    )
}

@Composable
fun WorkshopLeaderIcon(url: String, color: Color) {

    AsyncImage(
        model = url,
        placeholder = painterResource(id = R.drawable.subtract),
        contentDescription = "The delasign logo",
        contentScale = ContentScale.Crop,

        modifier = Modifier
            .offset(x = 34.dp, y = 53.dp)
            .width(65.dp)
            .height(65.dp)
            .clip(CircleShape)
            .border(2.dp, color, CircleShape),


        )
}

@Composable
fun WorkshopMain(
    workshopStartDate: String, workshopEndDate: String, imageDescription: String,
    url: String, name: String, workshopName: String,
    workshopHoursAndMinutes: String, workshopDayOfWeek: String,
    skillColor: Color, prerequisite: String, onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(color = GreyBackground)
            .fillMaxSize()
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {
            WorkshopBackIcon(onNavigateBack)
            WorkshopHeader()
        }

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            WorkshopInfoBox(url, name, workshopName, skillColor)
            WorkshopDateText(workshopStartDate, workshopEndDate)
            WorkshopMainText(imageDescription)
            WorkshopPrerequisite(prerequisite)
            WorkshopLine()
            WorkshopChooseDateText()
            WorkshopChooseDateBox(workshopStartDate, workshopHoursAndMinutes, workshopDayOfWeek)
        }
    }

}

