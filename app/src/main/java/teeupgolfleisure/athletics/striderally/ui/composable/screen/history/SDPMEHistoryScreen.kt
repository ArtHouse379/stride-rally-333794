package teeupgolfleisure.athletics.striderally.ui.composable.screen.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import teeupgolfleisure.athletics.striderally.R
import teeupgolfleisure.athletics.striderally.data.entity.WorkoutSessionEntity
import teeupgolfleisure.athletics.striderally.ui.composable.approot.LocalContentBottomPadding
import teeupgolfleisure.athletics.striderally.ui.composable.screen.history.calendar.HistoryCalendar
import teeupgolfleisure.athletics.striderally.ui.composable.screen.history.card.WorkoutSessionCard
import teeupgolfleisure.athletics.striderally.ui.composable.screen.shared.DataEmptyContent
import teeupgolfleisure.athletics.striderally.ui.state.DataUiState
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens
import teeupgolfleisure.athletics.striderally.ui.viewmodel.HistoryViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

private val DividerThickness = 1.dp

@Composable
fun SDPMEHistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = koinViewModel(),
) {
    val sessionsState by viewModel.sessionsState.collectAsStateWithLifecycle()
    val selectedDate by viewModel.selectedDate.collectAsStateWithLifecycle()

    val sessions =
        (sessionsState as? DataUiState.Populated<List<WorkoutSessionEntity>>)?.data.orEmpty()

    HistoryContent(
        sessions = sessions,
        selectedDate = selectedDate,
        onDateSelected = viewModel::onDateSelected,
        modifier = modifier,
    )
}

@Composable
private fun HistoryContent(
    sessions: List<WorkoutSessionEntity>,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    val markedDates = remember(sessions) {
        sessions.map { it.timestamp.toLocalDate() }.toSet()
    }
    val sessionsForSelectedDate = remember(sessions, selectedDate) {
        sessions.filter { it.timestamp.toLocalDate() == selectedDate }
    }

    Column(modifier = modifier.fillMaxSize()) {
        HistoryCalendar(
            selectedDate = selectedDate,
            markedDates = markedDates,
            onDateSelected = onDateSelected,
            modifier = Modifier.padding(
                start = dimens.screenPadding,
                end = dimens.screenPadding,
                top = dimens.screenPadding,
                bottom = dimens.contentSpacing,
            ),
        )

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = dimens.screenPadding),
            thickness = DividerThickness,
            color = MaterialTheme.colorScheme.outlineVariant,
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                start = dimens.screenPadding,
                end = dimens.screenPadding,
                top = dimens.contentSpacing,
                bottom = dimens.screenPadding + LocalContentBottomPadding.current,
            ),
            verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
        ) {
            if (sessionsForSelectedDate.isEmpty()) {
                item {
                    DataEmptyContent(
                        primaryText = stringResource(R.string.history_day_empty),
                        modifier = Modifier.fillParentMaxSize(),
                    )
                }
            } else {
                items(sessionsForSelectedDate, key = { session -> session.id }) { session ->
                    WorkoutSessionCard(session = session)
                }
            }
        }
    }
}
