package org.tumo.MyTumo.service.extra

import com.google.gson.annotations.SerializedName
import org.tumo.MyTumo.service.format.formatDate
import java.util.Date

data class StudentSchedule(
    @SerializedName("type") val type: String,
    @SerializedName("tags") val tags: List<String>? = emptyList(),
    @SerializedName("date") val date: Date,

    ) {
    val dateStringExtra: String
        get() = formatDate(date, "HH:mm EE")
    val dayStringExtra: String
        get() = formatDate(date, "MMMM dd")
}

