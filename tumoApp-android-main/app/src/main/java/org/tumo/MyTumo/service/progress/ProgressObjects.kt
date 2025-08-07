package org.tumo.MyTumo.service.progress

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName
import org.tumo.MyTumo.service.format.formatDate
import org.tumo.MyTumo.service.student.Schedule
import org.tumo.MyTumo.ui.theme.AnimationColor
import org.tumo.MyTumo.ui.theme.DrawingColor
import org.tumo.MyTumo.ui.theme.FilmMakingColor
import org.tumo.MyTumo.ui.theme.GamingColor
import org.tumo.MyTumo.ui.theme.GraphicDesignColor
import org.tumo.MyTumo.ui.theme.Modeling3DColor
import org.tumo.MyTumo.ui.theme.PhotographyColor
import org.tumo.MyTumo.ui.theme.ProgrammingColor
import org.tumo.MyTumo.ui.theme.RoboticsColor
import org.tumo.MyTumo.ui.theme.WebDevelopmentColor
import org.tumo.MyTumo.ui.theme.WritingColor
import java.util.Date

data class StudentProgress(
    @SerializedName("progress") val progress: Progress,
    @SerializedName("overview") val overview: Overview,
)

data class Progress(
    @SerializedName("events") val events: List<Event>,
)

data class Event(
    val id: String,
    val skill: String,
    val type: String,
    val startDate: Date,
    val endDate: Date,
    val status: String,
    val title: Text,
    val description: Text,
    val schedule: Schedule,
    val prerequisites: List<String>?
) {
    val dateString: String
        get() = formatDate(startDate, "YYYY-dd-MM")
}

data class Text(
    val en: String,
    val hy: String,
)

data class Overview(
    val activitiesCount: Int, val eventsCount: Int
)

enum class SphereColors(val skill: String, val color: Color) {
    Drawing("drawing", color = DrawingColor),
    Writing("writing", WritingColor),
    FilmMaking("filmmaking", FilmMakingColor),
    GraphicDesign("graphic-design", GraphicDesignColor),
    Robotics("robotics", RoboticsColor),
    Photography("photography", PhotographyColor),
    WebDevelopment("web-development", WebDevelopmentColor),
    Animation("animation", AnimationColor),
    Modeling3D("3d-modeling", Modeling3DColor),
    Programming("programming", ProgrammingColor),
    GameDevelopment("game-development", GamingColor),
    MusicColor("music", org.tumo.MyTumo.ui.theme.MusicColor),
    MotionGraphic("motion-graphics", org.tumo.MyTumo.ui.theme.MotionGraphicColor),
    NewMedia("new-media", org.tumo.MyTumo.ui.theme.NewMediaColor),
}

fun findColorBySkillName(skillName: String?): Color? {
    val matchingSkill = SphereColors.values().find { it.skill == skillName }
    return matchingSkill?.color
}

