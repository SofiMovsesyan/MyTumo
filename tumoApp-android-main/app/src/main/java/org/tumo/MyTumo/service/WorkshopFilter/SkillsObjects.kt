package org.tumo.MyTumo.service.WorkshopFilter

import com.google.gson.annotations.SerializedName

data class WorkshopSkill(
    @SerializedName("id")
    val id: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("title")
    val title: SkillTitle,
    @SerializedName("name")
    val name: String,
    var selected: Boolean = false
)

data class SkillTitle(
    @SerializedName("en")
    val english: String,
    @SerializedName("hy")
    val armenian: String
)