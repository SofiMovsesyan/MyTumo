package org.tumo.MyTumo.ui.attendance.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.tumo.MyTumo.R
import org.tumo.MyTumo.data.mockAttendance
import org.tumo.MyTumo.service.attendance.StudentAttendance

@Composable
fun AbsentIcon(isActive: Boolean) {
    Image(
        painter = painterResource(
            id = if (isActive) R.drawable.ic_absent_active else R.drawable.ic_absent_inactive
        ), contentDescription = null, modifier = Modifier
            .size(18.dp)
            .padding(1.dp)
    )
}

@Composable
fun PresentIcon(isActive: Boolean) {
    Image(
        painter = painterResource(
            id = if (isActive) R.drawable.ic_present_active else R.drawable.ic_present_inactive
        ), contentDescription = null, modifier = Modifier.size(18.dp)
    )
}

@Composable
fun AttendanceRow(attendance: StudentAttendance) {
    val isOneTime = attendance.type != "Onetime" && attendance.type != "Regular"
    Row(
        modifier = Modifier
            .height(25.dp)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = attendance.dateString, fontSize = 14.sp
        )
        Text(
            text = " | ", fontSize = 12.sp
        )
        Text(
            text = attendance.sessionWeekString, fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = attendance.sessionStartString, fontSize = 14.sp
        )
        Text(
            text = " - ", fontSize = 12.sp
        )
        Text(
            text = attendance.sessionEndString, fontSize = 14.sp
        )
        Spacer(modifier = Modifier.weight(1F))
        if (attendance.status == "Absent") {
            AbsentIcon(!isOneTime)
        } else {
            PresentIcon(!isOneTime)
        }
        Spacer(modifier = Modifier.width(5.dp))
        Divider(
            color = Color.Gray, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Spacer(modifier = Modifier.width(5.dp))
        if (attendance.status == "Absent") {
            AbsentIcon(isOneTime)
        } else {
            PresentIcon(isOneTime)
        }
    }
}

@Composable
fun ExerciseRow() {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = stringResource(R.string.activity_workshop), fontSize = 12.sp, modifier = Modifier

        )
    }
}

@Composable
fun SeeMore() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.see_more),
                color = Color.Gray,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                painter = painterResource(id = R.drawable.polygon_2),
                contentDescription = null,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RowPreview() {
    AttendanceRow(attendance = mockAttendance)
}