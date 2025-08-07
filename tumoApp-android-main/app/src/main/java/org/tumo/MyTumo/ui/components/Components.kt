package org.tumo.MyTumo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.tumo.MyTumo.R

@Composable
fun MyTumoAppBar(
    content: String,
    showRightIcon: Boolean? = false,
    showLeftIcon: Boolean? = false,
    onRightIconPressed: () -> Unit = {},
    onLeftIconPressed: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .padding(horizontal = 30.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        if (showLeftIcon == true) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .height(24.dp)
                    .clickable(onClick = onLeftIconPressed),
                contentDescription = "Back",
            )
        }
        Text(
            text = content, style = TextStyle(
                fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
            ), modifier = Modifier.weight(1F), color = MaterialTheme.colorScheme.primary
        )
        if (showRightIcon == true) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_settings),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .height(24.dp)
                    .clickable(onClick = onRightIconPressed),
                contentDescription = "Settings",
            )
        }
    }
}

@Composable
fun CenterAlignedProgressIndicator() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
    ) {
        CircularProgressIndicator(modifier = Modifier.size(30.dp))
    }
}
