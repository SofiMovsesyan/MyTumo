package org.tumo.MyTumo.ui.schedule

import androidx.compose.ui.graphics.Color
import org.tumo.MyTumo.R
import org.tumo.MyTumo.ui.theme.AnimationColor
import org.tumo.MyTumo.ui.theme.DrawingColor
import org.tumo.MyTumo.ui.theme.FilmMakingColor
import org.tumo.MyTumo.ui.theme.GameDevelopmentColor
import org.tumo.MyTumo.ui.theme.GraphicDesignColor
import org.tumo.MyTumo.ui.theme.MotionGraphicsColor
import org.tumo.MyTumo.ui.theme.MusicColor
import org.tumo.MyTumo.ui.theme.NewMediaColor
import org.tumo.MyTumo.ui.theme.PhotographyColor
import org.tumo.MyTumo.ui.theme.ProgrammingColor
import org.tumo.MyTumo.ui.theme.RoboticsColor
import org.tumo.MyTumo.ui.theme.ThreeModelingColor
import org.tumo.MyTumo.ui.theme.WebDevelopmentColor
import org.tumo.MyTumo.ui.theme.WritingColor

enum class Skill(val nameKey: String, val color: Color, val imageResId: Int, val text: String) {
    THREE_D_MODELING("3d-modeling", ThreeModelingColor, R.drawable.three_d_modeling, "3Դ Մոդելավորում"),
    ANIMATION("animation", AnimationColor, R.drawable.animation, "Անիմացիա"),
    DRAWING("drawing", DrawingColor, R.drawable.drawing, "Նկարչություն"),
    FILMMAKING("filmmaking", FilmMakingColor, R.drawable.filmmaking,"Ֆիլմերի ստեղծում",),
    GAME_DEVELOPMENT("game-development", GameDevelopmentColor, R.drawable.game_development, "Խաղերի ստեղծում"),
    GRAPHIC_DESIGN("graphic-design", GraphicDesignColor, R.drawable.graphic_design, "Գևաֆիկ դիզայն"),
    MOTION_GRAPHICS("motion-graphics", MotionGraphicsColor, R.drawable.motion_graphics, "Շարժական գրաֆիկա"),
    MUSIC("music", MusicColor, R.drawable.music, "Երաժշտություն"),
    NEW_MEDIA("new-media", NewMediaColor, R.drawable.new_media, "Նոր մեդիա"),
    PHOTOGRAPHY("photography", PhotographyColor, R.drawable.photography, "Լուսանկարչություն",),
    PROGRAMMING("programming", ProgrammingColor, R.drawable.programming, "Ծրագրավորում"),
    ROBOTICS("robotics", RoboticsColor, R.drawable.robotics, "Ռոբոտաշինություն"),
    WEB_DEVELOPMENT("web-development", WebDevelopmentColor, R.drawable.web_development, "Կայքերի մշակում"),
    WRITING("writing", WritingColor, R.drawable.writing, "Ստեղծագրություն");

    companion object {
        fun getColorByNameKey(nameKey: String): Color {
            return try {
                values().first { it.nameKey == nameKey }.color
            } catch (e: NoSuchElementException) {
                Color.Gray
            }
        }


    fun getImageResIdByNameKey(nameKey: String): Int {
        return try {
            values().first { it.nameKey == nameKey }.imageResId
        } catch (e: NoSuchElementException) {
            R.drawable.event_icon
        }
    }

    fun getTextByNameKey(nameKey: String): String {
        return try {
            values().first { it.nameKey == nameKey }.text
        } catch (e: NoSuchElementException) {
            "Նկարչություն"
        }
    }
}


}