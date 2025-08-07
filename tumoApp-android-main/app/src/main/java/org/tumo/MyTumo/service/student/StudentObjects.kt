package org.tumo.MyTumo.service.student

import com.google.gson.annotations.SerializedName

data class StudentInfo(
    @SerializedName("id") val id: String,
    @SerializedName("domainId") val domainId: String,
    @SerializedName("locationId") val locationId: String,
    @SerializedName("dob") val dob: String,
    @SerializedName("gender") val gender: Int,
    @SerializedName("status") val status: Int,
    @SerializedName("tumoId") val tumoId: String,
    @SerializedName("coach") val coach: Coach,
    @SerializedName("schedule.kt") val schedule: Schedule,
    @SerializedName("email") val email: String,
    @SerializedName("firstName") val firstName: Name,
    @SerializedName("middleName") val middleName: Name,
    @SerializedName("lastName") val lastName: Name,
    @SerializedName("photoUrl") val photoUrl: String?
)

data class Coach(
    @SerializedName("id") val id: String,
    @SerializedName("photo") val photo: String?,
    @SerializedName("firstName") val firstName: Name,
    @SerializedName("middleName") val middleName: Name,
    @SerializedName("lastName") val lastName: Name
)

data class Schedule(
    @SerializedName("id") val id: String,
    @SerializedName("weekday") val weekday: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") val endTime: String
)

data class Name(
    @SerializedName("en") val en: String,
    @SerializedName("hy") val hy: String
)
