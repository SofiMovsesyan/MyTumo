package org.tumo.MyTumo.ui.attendance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.tumo.MyTumo.data.mockAttendances
import org.tumo.MyTumo.service.attendance.StudentAttendance
import org.tumo.MyTumo.ui.attendance.components.AttendanceRow
import org.tumo.MyTumo.ui.attendance.components.ExerciseRow
import org.tumo.MyTumo.ui.attendance.components.SeeMore
import org.tumo.MyTumo.ui.theme.MyTumoTheme


@Composable
fun ProfileAttendance(attendanceInfo: List<StudentAttendance>?, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        attendanceInfo?.let { ProfileAttendanceContent(attendanceInfo = it) } ?: run {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                CircularProgressIndicator(modifier = Modifier.size(30.dp))
            }
        }
    }
}

@Composable
fun ProfileAttendanceContent(attendanceInfo: List<StudentAttendance>) {
    Surface(modifier = Modifier.clip(RoundedCornerShape(16.dp))) {
        Column(
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 5.dp
            )
        ) {
            ExerciseRow()
            Spacer(modifier = Modifier.height(5.dp))
            attendanceInfo.let {
                for (i in 0..3) {
                    AttendanceRow(attendanceInfo[i])
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            SeeMore()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview()
@Composable
fun ProfileAttendancePreview() {
    MyTumoTheme() {
        ProfileAttendanceContent(mockAttendances)
    }

}






