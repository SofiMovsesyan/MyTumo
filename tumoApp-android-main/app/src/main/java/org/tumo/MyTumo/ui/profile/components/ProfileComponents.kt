package org.tumo.MyTumo.ui.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.tumo.MyTumo.R
import org.tumo.MyTumo.service.student.Coach


@Composable
fun WorkshopInfoItem(count: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count.toString(), style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = Bold,
            )
        )
        Text(
            text = stringResource(R.string.activity), style = TextStyle(
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Composable
fun EventInfoItem(count: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = count.toString(), style = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = Bold,
            )
        )
        Text(
            text = stringResource(R.string.workshop), style = TextStyle(
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Composable
fun CoachRow(coach: Coach, language: Int) {
    val fullName = remember { mutableStateOf("") }

    if (language == 1) {
        fullName.value = "${coach.firstName.hy} ${coach.middleName.hy} ${coach.lastName.hy}"
    } else {
        fullName.value = "${coach.firstName.en} ${coach.middleName.en} ${coach.lastName.en}"
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        coach.photo?.let {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(it)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_user),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(38.dp)
            )
        } ?: run {
            Image(
                painter = painterResource(R.drawable.ic_user),
                contentDescription = null,
                modifier = Modifier.size(38.dp)
            )
        }

        Spacer(modifier = Modifier.width(13.dp))
        Text(text = fullName.value, style = TextStyle(color = Color.Black, fontWeight = Bold))

    }
}