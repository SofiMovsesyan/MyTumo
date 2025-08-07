package org.tumo.MyTumo.service.format

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val armenianLocale = Locale("hy", "AM")

fun formatDate(date: Date?, pattern: String): String {
    if (date == null){
        return ""
    }
    val dateFormat = SimpleDateFormat(pattern, armenianLocale)
    return dateFormat.format(date)
}