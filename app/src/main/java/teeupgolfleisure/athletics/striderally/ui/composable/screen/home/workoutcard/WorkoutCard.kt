package teeupgolfleisure.athletics.striderally.ui.composable.screen.home.workoutcard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import teeupgolfleisure.athletics.striderally.data.model.WorkoutModel
import teeupgolfleisure.athletics.striderally.ui.composable.screen.home.workoutcard.content.WorkoutCardContent
import teeupgolfleisure.athletics.striderally.ui.composable.screen.home.workoutcard.shell.WorkoutCardShell

@Composable
fun WorkoutCard(
    workout: WorkoutModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val info = WorkoutCardInfo(
        name = workout.name,
        typeLabel = stringResource(workout.type.titleRes),
        typeIcon = workout.type.icon,
        difficultyLabel = stringResource(workout.difficulty.titleRes),
        difficultyLevel = workout.difficulty.level,
        exerciseCount = workout.exercises.size,
    )

    WorkoutCardShell(
        imageUrl = workout.imageUrl,
        info = info,
        onClick = onClick,
        modifier = modifier,
    ) { cardInfo -> WorkoutCardContent(cardInfo) }
}
