package teeupgolfleisure.athletics.striderally.ui.composable.screen.home.workoutcard.content

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import teeupgolfleisure.athletics.striderally.R
import teeupgolfleisure.athletics.striderally.ui.composable.screen.home.workoutcard.WorkoutCardInfo
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens

private val ChipIconSize = 14.dp

@Composable
fun WorkoutCardContent(info: WorkoutCardInfo, modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        Text(
            text = info.name,
            modifier = Modifier.padding(horizontal = dimens.cardPadding),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = dimens.cardPadding),
            horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
        ) {
            InfoChip(icon = info.typeIcon, text = info.typeLabel)
            InfoChip(icon = null, text = info.difficultyLabel)
        }

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        Text(
            text = stringResource(R.string.workout_card_exercise_count, info.exerciseCount),
            modifier = Modifier.padding(horizontal = dimens.cardPadding),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun InfoChip(
    icon: ImageVector?,
    text: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(dimens.controlCornerRadius),
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = dimens.itemSpacing,
                vertical = dimens.tightSpacing
            ),
            horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(ChipIconSize),
                )
            }

            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
