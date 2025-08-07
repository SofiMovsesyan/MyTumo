package org.tumo.MyTumo.ui.schedule

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.tumo.MyTumo.R
import org.tumo.MyTumo.service.event.EventInfo
import org.tumo.MyTumo.ui.theme.BackgroudGray
import org.tumo.MyTumo.ui.theme.Purple200

@Composable
fun EventSchedule(
    eventInfos: List<EventInfo>,
    onNavigateToMySchedule: (eventInfo: EventInfo) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroudGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PageTitle(stringResource(id = R.string.my_schedule_title), color = Purple200)
        LazyColumn {
            items(eventInfos.size) { index ->
                val eventInfo = eventInfos[index]
                Box(
                    modifier = Modifier.clickable {
                        onNavigateToMySchedule(eventInfo)
                    }
                ) {
                    EventColumn(eventInfo)
                }
            }
            item {
                Spacer(modifier = Modifier.height(90.dp))
            }
        }
    }
}


@Composable
fun PageTitle(name: String, color: Color) {
    Text(
        text = name,
        color = color,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.padding(
            top = 40.dp,
            start = 10.dp,
            end = 20.dp,
            bottom = 20.dp
        )
    )
}


@Composable
fun EventColumn(eventInfo: EventInfo) {
    val skill = Skill.values().find { it.nameKey == eventInfo.skill }
    val color = skill?.let { Skill.getColorByNameKey(it.nameKey) } ?: Color.Gray
    val title = eventInfo.title.armenian
    val imageResId = skill?.imageResId ?: R.drawable.event_icon
    val startDate = eventInfo.getFormattedStartDate()
    val endDate = eventInfo.getFormattedEndDate()

    val status = eventInfo.status
    val statusImageResId = when (status) {
        "upcoming" -> R.drawable.yellow_time
        "ongoing" -> R.drawable.green_time
        else -> R.drawable.event_icon
    }

    Column(
        modifier = Modifier
            .padding(
                top = 15.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            )
            .background(color, shape = MaterialTheme.shapes.medium)
            .size(342.dp, 240.dp)
    ) {
        Text(
            text = "$startDate - $endDate",
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = Color.White
            ),
            modifier = Modifier
                .padding(top = 15.dp)
                .align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 40.dp)
                    .clip(CircleShape)
                    .size(81.dp)
                    .align(Alignment.CenterVertically)

            ) {
                Image(
                    painter = painterResource(imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .border(1.dp, Color.White, CircleShape),
                    contentScale = ContentScale.Fit
                )
            }


            Spacer(modifier = Modifier.width(18.dp))

            Image(
                painter = painterResource(statusImageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(22.dp)
                    .align(Alignment.CenterVertically)
                    .border(2.dp, Color.White, CircleShape),
                contentScale = ContentScale.Fit
            )
        }

        Text(
            text = title,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier
                .padding(top = 15.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
    }
}
