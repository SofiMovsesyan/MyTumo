package org.tumo.MyTumo.service.workshop

import android.icu.text.SimpleDateFormat
import com.google.gson.annotations.SerializedName
import java.util.*

data class WorkshopDetails(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: String,
    @SerializedName("title") val title: MultilanguageTitle,
    @SerializedName("skill") val skill: String,
    @SerializedName("color") val color: String,
    @SerializedName("description") val description: MultilanguageDescription,
    @SerializedName("eventHosts") val eventHosts: List<EventHostForId>,
    @SerializedName("startDate") val startDate: Date,
    @SerializedName("endDate") val endDate: Date,
    @SerializedName("registrationDeadline") val registrationDeadline: String,
    @SerializedName("sessions") val sessions: List<Session>,
    @SerializedName("dependencies") val dependencies: List<Dependency>,
    @SerializedName("popularEvent") val popularEvent: Boolean,
    @SerializedName("mobileCompatibility") val mobileCompatibility: Boolean,
    @SerializedName("desktopCompatibility") val desktopCompatibility: Boolean,
    @SerializedName("subscriptionMode") val subscriptionMode: Int,
    @SerializedName("currentUserDoneDependencies") val currentUserDoneDependencies: Boolean,
    @SerializedName("currentUserSubscriptionStatus") val currentUserSubscriptionStatus: String?,
    @SerializedName("currentUserReachedSubscriptionLimit") val currentUserReachedSubscriptionLimit: Boolean
) {
    val startDateFormatted: String
        get() {
            return format(startDate)
        }

    val endDateFormatted: String
        get() {
            return format(endDate)
        }
    val startDateFormattedByHourAndMinute: String
        get() {
            return formatByHourAndMinute(startDate)
        }

    val endDateFormattedByHourAndMinute: String
        get() {
            return formatByHourAndMinute(endDate)
        }
    val startDateFormattedByDayOfWeek: String
        get() {
            return formatByDayOfWeek(startDate)
        }
}


private fun format(date: Date): String {
    val formatter = SimpleDateFormat("MMM dd", Locale.getDefault())
    return formatter.format(date)
}

private fun formatByHourAndMinute(timestamp: Date): String {
    val currentDate = timestamp.toString()
    val hour = Calendar.getInstance().apply {
        time =
            SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.ENGLISH).parse(currentDate)
    }.get(Calendar.HOUR_OF_DAY)
    val minute = Calendar.getInstance().apply {
        time =
            SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.ENGLISH).parse(currentDate)
    }.get(Calendar.MINUTE).toString().padStart(2, '0')
    return "$hour:$minute"
}

private fun formatByDayOfWeek(timestamp: Date): String {
    val currentDate = timestamp.toString()

    val month = Calendar.getInstance().apply {
        time =
            SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.ENGLISH).parse(currentDate)
    }.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH)
    return month
}

data class MultilanguageTitle(
    @SerializedName("en") val english: String,
    @SerializedName("hy") val armenian: String
)

data class MultilanguageDescription(
    @SerializedName("en") val english: String,
    @SerializedName("hy") val armenian: String
)

data class EventHostForId(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: Int,
    @SerializedName("photoUrl") val photoUrl: String,
    @SerializedName("name") val name: MultilanguageTitle
)

data class Dependency(
    @SerializedName("id")
    val id: String,
    @SerializedName("skill")
    val skill: String,
    @SerializedName("title")
    val title: TitleW
)


data class TitleW(
    @SerializedName("en")
    val english: String,
    @SerializedName("hy")
    val armenian: String
)

data class Session(
    @SerializedName("id")
    val id: String,
    @SerializedName("eventId")
    val eventId: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("showEventHosts")
    val showEventHosts: Boolean,
    @SerializedName("eventHosts")
    val eventHosts: List<EventHost>?,
    @SerializedName("subscriptions")
    val subscriptions: Int,
    @SerializedName("maxSeats")
    val maxSeats: Int?,
    @SerializedName("schedule")
    val schedule: List<Schedule>,
    @SerializedName("currentUserSubscriptionStatus")
    val currentUserSubscriptionStatus: String?,
    @SerializedName("currentUserWaitingReason")
    val currentUserWaitingReason: String?
)

data class Schedule(
    @SerializedName("id")
    val id: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String
)

