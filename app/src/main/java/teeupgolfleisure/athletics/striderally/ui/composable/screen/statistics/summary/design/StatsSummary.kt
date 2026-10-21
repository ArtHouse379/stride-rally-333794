package teeupgolfleisure.athletics.striderally.ui.composable.screen.statistics.summary.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import teeupgolfleisure.athletics.striderally.R
import teeupgolfleisure.athletics.striderally.data.entity.WorkoutSessionEntity
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens
import java.time.LocalDate

private val DividerHeight = 32.dp

@Composable
fun StatsSummary(
    sessions: List<WorkoutSessionEntity>,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    val totalWorkouts = sessions.size
    val completionRate = remember(sessions) {
        if (sessions.isEmpty()) 0 else (sessions.count { it.isFullyCompleted } * 100) / sessions.size
    }

    val currentStreak = remember(sessions) {
        val sessionDates = sessions.map { it.timestamp.toLocalDate() }.toSet()
        var streak = 0
        var day = LocalDate.now()
        while (sessionDates.contains(day)) {
            streak++
            day = day.minusDays(1)
        }
        streak
    }

    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(dimens.cardCornerRadius),
            )
            .padding(dimens.cardPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StatColumn(
            value = totalWorkouts.toString(),
            label = stringResource(R.string.statistics_summary_total),
            modifier = Modifier.weight(1f),
        )

        VerticalDivider(
            modifier = Modifier.height(DividerHeight),
            color = MaterialTheme.colorScheme.outlineVariant,
        )

        StatColumn(
            value = stringResource(R.string.statistics_summary_percent_format, completionRate),
            label = stringResource(R.string.statistics_summary_completion),
            modifier = Modifier.weight(1f),
        )

        VerticalDivider(
            modifier = Modifier.height(DividerHeight),
            color = MaterialTheme.colorScheme.outlineVariant,
        )

        StatColumn(
            value = stringResource(R.string.statistics_summary_days_format, currentStreak),
            label = stringResource(R.string.statistics_summary_streak),
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun StatColumn(
    value: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        Text(
            text = label,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
