package teeupgolfleisure.athletics.striderally.data.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.ui.graphics.vector.ImageVector
import teeupgolfleisure.athletics.striderally.R

data class WorkoutModel(
    val id: Int,
    val name: String,
    val exercises: List<ExerciseModel>,
    val difficulty: Difficulty,
    val type: WorkoutType,
    val imageUrl: String,
)

enum class Difficulty(@field:StringRes val titleRes: Int, val level: Int) {
    BEGINNER(R.string.difficulty_beginner, level = 1),
    INTERMEDIATE(R.string.difficulty_intermediate, level = 2),
    ADVANCED(R.string.difficulty_advanced, level = 3),
}

enum class WorkoutType(@field:StringRes val titleRes: Int, val icon: ImageVector) {
    ROAD_RUNNING(R.string.workout_type_road_running_title, Icons.AutoMirrored.Filled.DirectionsRun),
    TRAIL_RUNNING(R.string.workout_type_trail_running_title, Icons.Filled.Terrain),
    GROUP_RUNS(R.string.workout_type_group_runs_title, Icons.Filled.Groups),
    RACE_PREP(R.string.workout_type_race_prep_title, Icons.Filled.EmojiEvents),
}
