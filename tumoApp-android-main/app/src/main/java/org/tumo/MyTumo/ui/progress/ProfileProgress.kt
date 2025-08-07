package org.tumo.MyTumo.ui.progress

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.tumo.MyTumo.service.progress.Event
import org.tumo.MyTumo.ui.attendance.components.SeeMore
import org.tumo.MyTumo.ui.components.CenterAlignedProgressIndicator
import org.tumo.MyTumo.ui.progress.components.ProgressItem
import org.tumo.MyTumo.ui.theme.MyTumoTheme

@Composable
fun ProfileProgress(modifier: Modifier = Modifier, lastEvent: Event?) {
    Box(modifier = modifier) {
        Surface(modifier = Modifier.clip(RoundedCornerShape(16.dp))) {
            Column(
                modifier = Modifier.padding(
                    horizontal = 10.dp
                )
            ) {
                lastEvent?.let {
                    ProgressItem(lastEvent)
                } ?: run {
                    CenterAlignedProgressIndicator()
                }
                SeeMore()
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Preview
@Composable
fun ProfileAttendancePreview() {
    MyTumoTheme {
        ProfileProgress(Modifier, null)
    }

}
