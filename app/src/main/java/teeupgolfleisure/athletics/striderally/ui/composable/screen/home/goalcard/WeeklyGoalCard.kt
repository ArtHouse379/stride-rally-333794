package teeupgolfleisure.athletics.striderally.ui.composable.screen.home.goalcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import teeupgolfleisure.athletics.striderally.R
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens
import teeupgolfleisure.athletics.striderally.ui.theme.cardBorderStroke

@Composable
fun WeeklyGoalCard(
    weeklyGoal: Int,
    completed: Int,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        border = cardBorderStroke(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier
                .padding(dimens.cardPadding)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.home_weekly_goal_label).uppercase(),
                style = MaterialTheme.typography.labelMedium.copy(letterSpacing = 1.5.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = weeklyGoal.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                Icon(
                    imageVector = Icons.Filled.TrackChanges,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}
