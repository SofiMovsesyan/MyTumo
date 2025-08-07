package org.tumo.MyTumo.service.attendance

import com.google.gson.annotations.SerializedName
import org.tumo.MyTumo.service.format.formatDate
import java.util.Date

data class StudentAttendance(
    @SerializedName("id") val id: String,
    @SerializedName("status") val status: String,
    @SerializedName("type") val type: String,
    @SerializedName("sessionStart") val sessionStart:Date,
    @SerializedName("sessionEnd") val sessionEnd:Date,
    @SerializedName("date") val date: Date,

    ){
    val dateString: String
        get() = formatDate(date, "MMM dd")
    val sessionStartString: String
        get() = formatDate(sessionStart, "HH:mm")
    val sessionWeekString: String
        get() = formatDate(sessionStart, "EE")
    val sessionEndString: String
        get() = formatDate(sessionEnd, "HH:mm")

}
