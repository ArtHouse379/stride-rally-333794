package teeupgolfleisure.athletics.striderally.ui.composable.screen.workout.exercisecard.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import teeupgolfleisure.athletics.striderally.R
import teeupgolfleisure.athletics.striderally.data.model.ExerciseModel
import teeupgolfleisure.athletics.striderally.data.model.Intensity
import teeupgolfleisure.athletics.striderally.ui.state.ExerciseItemUiState
import teeupgolfleisure.athletics.striderally.ui.state.ExercisePhase
import teeupgolfleisure.athletics.striderally.ui.state.ExerciseStatus
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens

private val FlameIconSize = 12.dp
private val FlameLevelCount = Intensity.entries.size

@Composable
fun ExerciseCardContent(item: ExerciseItemUiState) {
    val dimens = LocalDimens.current
    val isActive = item.status == ExerciseStatus.ACTIVE
    val isCompleted = item.status == ExerciseStatus.COMPLETED

    Text(
        text = item.exercise.name,
        style = MaterialTheme.typography.titleMedium,
        color = if (isCompleted) {
            MaterialTheme.colorScheme.onSurfaceVariant
        } else {
            MaterialTheme.colorScheme.onSurface
        },
        textDecoration = if (isCompleted) TextDecoration.LineThrough else null,
        minLines = 2,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
    )

    Spacer(modifier = Modifier.height(dimens.tightSpacing))

    Text(
        text = if (isActive) item.activeSubtitle() else item.exercise.subtitle(),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )

    Spacer(modifier = Modifier.height(dimens.tightSpacing))

    IntensityIndicator(intensity = item.exercise.intensity)
}

@Composable
private fun IntensityIndicator(
    intensity: Intensity,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val filledCount = intensity.ordinal + 1

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
    ) {
        repeat(FlameLevelCount) { index ->
            Icon(
                imageVector = Icons.Filled.LocalFireDepartment,
                contentDescription = null,
                tint = if (index < filledCount) {
                    MaterialTheme.colorScheme.tertiary
                } else {
                    MaterialTheme.colorScheme.outlineVariant
                },
                modifier = Modifier.size(FlameIconSize),
            )
        }
    }
}

@Composable
private fun ExerciseItemUiState.activeSubtitle(): String {
    val phaseLabel = when (phase) {
        ExercisePhase.WORK -> stringResource(R.string.exercise_phase_work)
        ExercisePhase.REST -> stringResource(R.string.exercise_phase_rest)
    }
    return stringResource(
        R.string.exercise_active_subtitle,
        stringResource(R.string.exercise_set_progress, currentSet, totalSets),
        phaseLabel,
    )
}

@Composable
private fun ExerciseModel.subtitle(): String {
    return if (sets != null && repetitions != null) {
        stringResource(R.string.exercise_sets_reps, sets, repetitions)
    } else {
        val seconds = ((duration ?: 0L) / 1000).toInt()
        stringResource(R.string.exercise_duration_seconds, seconds)
    }
}
