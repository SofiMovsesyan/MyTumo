package org.tumo.MyTumo.ui.attendance

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.tumo.MyTumo.R
import org.tumo.MyTumo.service.attendance.StudentAttendance
import org.tumo.MyTumo.ui.attendance.components.AttendanceRow
import org.tumo.MyTumo.ui.attendance.components.ExerciseRow
import org.tumo.MyTumo.ui.components.CenterAlignedProgressIndicator
import org.tumo.MyTumo.ui.components.MyTumoAppBar
import org.tumo.MyTumo.ui.profile.ProfileRoutes
import org.tumo.MyTumo.viewmodels.AttendanceViewModel

@Composable
fun Attendances(navController: NavController?, paddingValues: PaddingValues = PaddingValues(0.dp)) {
    val attendanceViewModel = viewModel<AttendanceViewModel>()
    val attendanceInfo by attendanceViewModel.liveDataStudentAttendance.observeAsState()
    val limit = remember{ mutableStateOf(30) }

    LaunchedEffect(limit.value) {
        attendanceViewModel.getStudentAttendance(limit.value)
    }

    Scaffold(
        topBar = {
            MyTumoAppBar(content = stringResource(R.string.my_attendances),
                showRightIcon = true,
                showLeftIcon = true,
                onRightIconPressed = { navController?.navigate(ProfileRoutes.Settings.name) },
                onLeftIconPressed = { navController?.popBackStack() })
        },
    ) { PaddingValues ->
        Column {
            Surface(
                modifier = Modifier
                    .padding(PaddingValues)
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .weight(1F)
                    .fillMaxWidth()
            ) {
                attendanceInfo?.let {
                    AttendanceContent(it, limit.value, loadMoreContent = { limit.value = limit.value + 10 })
                } ?: run {
                    CenterAlignedProgressIndicator()
                }
            }
            Spacer(modifier = Modifier.padding(paddingValues))
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AttendanceContent(attendances: List<StudentAttendance>, limit: Int, loadMoreContent: ()->Unit = {}) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState) {
        stickyHeader {
            Surface {
                Box(modifier = Modifier.padding(vertical = 5.dp)) {
                    ExerciseRow()
                }

            }
        }
        items(attendances) { attendance ->
            AttendanceRow(attendance = attendance)
        }
        if (listState.isScrollInProgress &&
            listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size >= attendances.size &&
            attendances.size == limit // Add this check
        ) {
            Log.i("asd", "ProgressContent: ${attendances.size} $attendances")
            // Call a function to load more content
            loadMoreContent()
        }
    }
}