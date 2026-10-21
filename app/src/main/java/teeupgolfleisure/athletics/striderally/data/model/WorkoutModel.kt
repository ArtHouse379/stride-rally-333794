package teeupgolfleisure.athletics.striderally.data.model

import androidx.annotation.StringRes
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
}