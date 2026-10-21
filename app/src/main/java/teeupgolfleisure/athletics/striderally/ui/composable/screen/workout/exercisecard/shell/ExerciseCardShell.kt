package teeupgolfleisure.athletics.striderally.ui.composable.screen.workout.exercisecard.shell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import teeupgolfleisure.athletics.striderally.R
import teeupgolfleisure.athletics.striderally.ui.state.ExerciseItemUiState
import teeupgolfleisure.athletics.striderally.ui.state.ExerciseStatus
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens
import teeupgolfleisure.athletics.striderally.ui.theme.cardBorderStroke

@Composable
fun ExerciseCard(
    number: Int,
    item: ExerciseItemUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val dimens = LocalDimens.current
    val isActive = item.status == ExerciseStatus.ACTIVE
    val isCompleted = item.status == ExerciseStatus.COMPLETED

    val containerColor = when (item.status) {
        ExerciseStatus.ACTIVE -> MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
        ExerciseStatus.COMPLETED -> MaterialTheme.colorScheme.surfaceContainer
        ExerciseStatus.PENDING -> MaterialTheme.colorScheme.surfaceContainerLow
    }

    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = isActive,
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        border = cardBorderStroke(),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            disabledContainerColor = containerColor,
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(dimens.cardPadding)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                content()
            }

            Spacer(modifier = Modifier.width(dimens.itemSpacing))

            when {
                isCompleted -> Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )

                isActive && item.isTimed -> Text(
                    text = formatTimer(item.remainingMs),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                )

                isActive -> Text(
                    text = stringResource(R.string.exercise_tap_to_finish_set),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                )

                else -> Unit
            }
        }
    }
}

private fun formatTimer(remainingMs: Long): String {
    val totalSeconds = ((remainingMs + 999) / 1000).coerceAtLeast(0L)
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%d:%02d".format(minutes, seconds)
}
