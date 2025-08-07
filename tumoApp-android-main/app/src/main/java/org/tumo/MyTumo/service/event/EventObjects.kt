package org.tumo.MyTumo.service.event

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class EventInfo(
    @SerializedName("id")
    val id: String,

    @SerializedName("skill")
    val skill: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("startDate")
    val startDate: String,

    @SerializedName("endDate")
    val endDate: String,

    @SerializedName("status")
    val status: String,

    @SerializedName("title")
    val title: Title,

    @SerializedName("description")
    val description: Description,

    @SerializedName("schedule")
    val schedule: List<Schedule>,

    @SerializedName("prerequisites")
    val prerequisites: Prerequisites?
) {
    data class Title(
        @SerializedName("en")
        val english: String,

        @SerializedName("hy")
        val armenian: String
    )

    data class Description(
        @SerializedName("en")
        val english: String,

        @SerializedName("hy")
        val armenian: String
    )

    data class Schedule(
        @SerializedName("id")
        val id: String,

        @SerializedName("startDate")
        val startDate: String,

        @SerializedName("endDate")
        val endDate: String
    )

    data class Prerequisites(
        @SerializedName("activities")
        val activities: Activity?,

        @SerializedName("events")
        val events: Event?
    )

    data class Activity(
        @SerializedName("type")
        val type: String,

        @SerializedName("item")
        val item: Item
    )
    data class Event(
        @SerializedName("type")
        val type: String,

        @SerializedName("item")
        val item: Item
    )

    data class Item(
        @SerializedName("id")
        val id: String,

        @SerializedName("status")
        val status: Int,

        @SerializedName("skill")
        val skill: String,

        @SerializedName("icon")
        val icon: String?,

        @SerializedName("title")
        val title: Title
    )

    fun getFormattedStartDate(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("HY", "ARM"))
        val outputFormat = SimpleDateFormat("MMM dd", Locale("hy", "arm"))
        val startDateDate = inputFormat.parse(startDate)
        return outputFormat.format(startDateDate)
    }

    fun getFormattedEndDate(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("hy", "arm"))
        val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale("hy", "arm"))
        val endDateDate = inputFormat.parse(endDate)
        return outputFormat.format(endDateDate)
    }
}


