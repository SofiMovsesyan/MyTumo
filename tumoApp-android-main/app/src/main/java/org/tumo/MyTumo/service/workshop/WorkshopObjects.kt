package org.tumo.MyTumo.service.workshop

import com.google.gson.annotations.SerializedName

data class WorkshopInfo(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: LocalizedName,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("registrationDeadline") val registrationDeadline: String,
    @SerializedName("skill") val skill: String,
    @SerializedName("color") val color: String,
    @SerializedName("eventType") val eventType: String,
    @SerializedName("eventHosts") val eventHosts: List<EventHost>,
    @SerializedName("locationIds") val locationIds: List<String>,
    @SerializedName("coverPhotos") val coverPhotos: LocalizedImageUrls,
    @SerializedName("popularEvent") val popularEvent: Boolean,
    @SerializedName("mobileCompatibility") val mobileCompatibility: Boolean,
    @SerializedName("desktopCompatibility") val desktopCompatibility: Boolean,
    @SerializedName("subscriptionMode") val subscriptionMode: Int,
    @SerializedName("currentUserSubscriptionStatus") val currentUserSubscriptionStatus: String?,
    @SerializedName("currentUserWaitingReason") val currentUserWaitingReason: String?,
    @SerializedName("currentUserDoneDependencies") val currentUserDoneDependencies: Boolean
)

data class LocalizedName(
    @SerializedName("en") val english: String,
    @SerializedName("hy") val armenian: String
)

data class EventHost(
    @SerializedName("id") val id: String,
    @SerializedName("type") val type: Int,
    @SerializedName("photoUrl") val photoUrl: String,
    @SerializedName("name") val name: LocalizedName
)

data class LocalizedImageUrls(
    @SerializedName("en") val english: String,
    @SerializedName("hy") val armenian: String
)
