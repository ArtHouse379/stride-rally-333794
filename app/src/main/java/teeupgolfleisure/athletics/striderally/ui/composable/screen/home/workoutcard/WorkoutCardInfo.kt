package teeupgolfleisure.athletics.striderally.ui.composable.screen.home.workoutcard

import androidx.compose.ui.graphics.vector.ImageVector

data class WorkoutCardInfo(
    val name: String,
    val typeLabel: String,
    val typeIcon: ImageVector,
    val difficultyLabel: String,
    val difficultyLevel: Int,
    val exerciseCount: Int,
)
