package org.tumo.MyTumo.ui.progress.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.tumo.MyTumo.R
import org.tumo.MyTumo.service.progress.Event
import org.tumo.MyTumo.service.progress.findColorBySkillName
import org.tumo.MyTumo.ui.theme.BlueGrey50
import org.tumo.MyTumo.ui.theme.failRed
import org.tumo.MyTumo.ui.theme.successGreen

@Composable
fun ProgressItem(event: Event, language: Int = 1) {
    Surface(modifier = Modifier.clip(RoundedCornerShape(10.dp))) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = if (event.status == "pass") stringResource(R.string.passed) else stringResource(
                        R.string.failed
                    ),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(800),
                        color = if (event.status == "pass") successGreen else failRed
                    )
                )
                Spacer(modifier = Modifier.width(5.dp))
                if (event.status == "pass") {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_present_active),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                } else {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_absent_active),
                        contentDescription = null,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.5.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = event.dateString,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = BlueGrey50,
                )

            )
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                color = findColorBySkillName(event.skill) ?: Color.Gray
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (language == 1) event.title.hy else event.title.en,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}