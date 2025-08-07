package org.tumo.MyTumo.ui.extraschedule

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.tumo.MyTumo.R
import org.tumo.MyTumo.data.mockExtra
import org.tumo.MyTumo.service.extra.StudentSchedule
import org.tumo.MyTumo.ui.components.CenterAlignedProgressIndicator
import org.tumo.MyTumo.ui.components.MyTumoAppBar
import org.tumo.MyTumo.ui.profile.ProfileRoutes
import org.tumo.MyTumo.ui.theme.Grey95
import org.tumo.MyTumo.ui.theme.MyTumoTheme
import org.tumo.MyTumo.ui.theme.ScheduleBlueAccentActive
import org.tumo.MyTumo.ui.theme.ScheduleBlueAccentInactive
import org.tumo.MyTumo.ui.theme.ScheduleBlueActive
import org.tumo.MyTumo.ui.theme.ScheduleBlueInactive
import org.tumo.MyTumo.ui.theme.ScheduleGreenActive
import org.tumo.MyTumo.ui.theme.ScheduleOrangeAccentActive
import org.tumo.MyTumo.ui.theme.ScheduleOrangeAccentInactive
import org.tumo.MyTumo.ui.theme.ScheduleOrangeActive
import org.tumo.MyTumo.ui.theme.ScheduleOrangeInactive
import org.tumo.MyTumo.viewmodels.ExtraViewModel
import java.util.Date

@Composable
fun Schedule(navController: NavController?, paddingValues: PaddingValues = PaddingValues(0.dp)) {
    val extraViewModel = viewModel<ExtraViewModel>()
    val studentSchedule by extraViewModel.liveDataStudentSchedule.observeAsState()

    LaunchedEffect(Unit) {
        extraViewModel.getStudentExtra()
    }

    Scaffold(
        topBar = {
            MyTumoAppBar(content = stringResource(R.string.extra_reservation),
                showLeftIcon = true,
                showRightIcon = true,
                onRightIconPressed = { navController?.navigate(ProfileRoutes.Settings.name) },
                onLeftIconPressed = { navController?.popBackStack() })
        },
    ) { PaddingValues ->
        Box(
            modifier = Modifier
                .padding(PaddingValues)
        ) {
            studentSchedule?.let {
                ScheduleContent(it, paddingValues)
            } ?: run { CenterAlignedProgressIndicator() }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScheduleContent(schedule: List<StudentSchedule>, paddingValues: PaddingValues, modifier: Modifier = Modifier, loadMoreContent: ()->Unit = {}) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState, modifier = modifier) {
        stickyHeader {
            Surface {
                ScheduleHeader()
            }
        }
        items(schedule) { item ->
            Column(Modifier.padding(horizontal = 20.dp)) {
                Spacer(modifier = Modifier.height(10.dp))
                ScheduleItem(item)

            }
        }
        item {
            Spacer(modifier = Modifier.height(paddingValues.calculateBottomPadding()))
        }
        if (listState.isScrollInProgress &&
            listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size >= schedule.size
        ) {
            // Call a function to load more content
            loadMoreContent()
        }
    }

}


@Composable
fun ScheduleItem(studentSchedule: StudentSchedule) {
    val isUpcoming = studentSchedule.date.after(Date())
    val mainColor: Color
    val accentColor: Color
    if (studentSchedule.type == "event") {
        if (isUpcoming){
            mainColor = ScheduleOrangeActive
            accentColor = ScheduleOrangeAccentActive
        } else{
            mainColor = ScheduleOrangeInactive
            accentColor = ScheduleOrangeAccentInactive
        }
    }else{
        if (isUpcoming){
            mainColor = ScheduleBlueActive
            accentColor = ScheduleBlueAccentActive
        } else{
            mainColor = ScheduleBlueInactive
            accentColor = ScheduleBlueAccentInactive
        }
    }

        Surface(
            color = mainColor,
            modifier = Modifier.clip(RoundedCornerShape(10.dp))
        ) {
            Column(
                modifier = Modifier.wrapContentHeight()
                    .padding(horizontal = 12.dp)
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = studentSchedule.dateStringExtra,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(800),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    ),
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = studentSchedule.dayStringExtra,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(35.dp)
                        .clip(RoundedCornerShape(7.dp)),
                    color = accentColor
                ) {
                    Row(
                        Modifier.padding(vertical = 2.dp, horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (studentSchedule.tags?.contains("offline") == true) stringResource(
                                R.string.from_tumo
                            ) else stringResource(R.string.from_home), style = TextStyle(
                                fontSize = 12.sp, fontWeight = FontWeight(600), color = Grey95
                            )
                        )
                        Spacer(modifier = Modifier.weight(1F))
                        Image(
                            modifier = Modifier.fillMaxHeight(),
                            painter = painterResource(R.drawable.tumo_icon),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Grey95)
                        )

                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
}

@Composable
fun ScheduleHeader() {
    Row(modifier = Modifier.height(50.dp)) {
        ScheduleHeaderItem(
            stringResource(R.string.workshop_schedule), ScheduleOrangeActive, Modifier.weight(1F)
        )
        ScheduleHeaderItem(
            stringResource(R.string.self_learning_schedule), ScheduleBlueActive, Modifier.weight(1F)
        )
        ScheduleHeaderItem(
            stringResource(R.string.additional_dates), ScheduleGreenActive, Modifier.weight(1F)
        )
    }
}

@Composable
fun ScheduleHeaderItem(text: String, color: Color, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        androidx.compose.material.Text(
            text = text, style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.W800,
                color = color,
                textAlign = TextAlign.Center
            ), modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.weight(1F))
        Divider(
            modifier = Modifier
                .width(133.dp)
                .height(5.dp), color = color
        )
    }
}

@Preview
@Composable
fun Preview() {
    MyTumoTheme {
        ScheduleItem(mockExtra)
    }
}