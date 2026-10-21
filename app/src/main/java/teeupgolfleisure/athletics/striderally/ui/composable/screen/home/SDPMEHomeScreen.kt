package teeupgolfleisure.athletics.striderally.ui.composable.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import teeupgolfleisure.athletics.striderally.R
import teeupgolfleisure.athletics.striderally.data.model.Difficulty
import teeupgolfleisure.athletics.striderally.data.model.TipModel
import teeupgolfleisure.athletics.striderally.data.model.WorkoutModel
import teeupgolfleisure.athletics.striderally.data.model.WorkoutType
import teeupgolfleisure.athletics.striderally.ui.composable.approot.LocalContentBottomPadding
import teeupgolfleisure.athletics.striderally.ui.composable.screen.home.filter.WorkoutFilterRow
import teeupgolfleisure.athletics.striderally.ui.composable.screen.home.layout.homeTopSection
import teeupgolfleisure.athletics.striderally.ui.composable.screen.home.workoutcard.WorkoutCard
import teeupgolfleisure.athletics.striderally.ui.composable.screen.shared.DataBasedContainer
import teeupgolfleisure.athletics.striderally.ui.composable.screen.shared.DataEmptyContent
import teeupgolfleisure.athletics.striderally.ui.state.DataUiState
import teeupgolfleisure.athletics.striderally.ui.state.WorkoutFilterMode
import teeupgolfleisure.athletics.striderally.ui.theme.ControlDivider
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens
import teeupgolfleisure.athletics.striderally.ui.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SDPMEHomeScreen(
    onWorkoutClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val workoutsState by viewModel.workoutsState.collectAsStateWithLifecycle()
    val filterMode by viewModel.filterMode.collectAsStateWithLifecycle()
    val selectedType by viewModel.selectedType.collectAsStateWithLifecycle()
    val selectedDifficulty by viewModel.selectedDifficulty.collectAsStateWithLifecycle()
    val weeklyGoal by viewModel.weeklyGoal.collectAsStateWithLifecycle()
    val weeklyProgress by viewModel.weeklyProgress.collectAsStateWithLifecycle()
    val tips by viewModel.tips.collectAsStateWithLifecycle()

    HomeContent(
        workoutsState = workoutsState,
        filterMode = filterMode,
        selectedType = selectedType,
        selectedDifficulty = selectedDifficulty,
        weeklyGoal = weeklyGoal,
        weeklyProgress = weeklyProgress,
        tips = tips,
        onFilterModeToggled = viewModel::onFilterModeToggled,
        onTypeSelected = viewModel::onTypeSelected,
        onDifficultySelected = viewModel::onDifficultySelected,
        onWorkoutClick = onWorkoutClick,
        modifier = modifier,
    )
}

@Composable
private fun HomeContent(
    workoutsState: DataUiState<List<WorkoutModel>>,
    filterMode: WorkoutFilterMode,
    selectedType: WorkoutType?,
    selectedDifficulty: Difficulty?,
    weeklyGoal: Int,
    weeklyProgress: Int,
    tips: List<TipModel>,
    onFilterModeToggled: () -> Unit,
    onTypeSelected: (WorkoutType?) -> Unit,
    onDifficultySelected: (Difficulty?) -> Unit,
    onWorkoutClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    DataBasedContainer(
        modifier = modifier.fillMaxSize(),
        dataState = workoutsState,

        dataPopulated = {
            val workouts = (workoutsState as DataUiState.Populated<List<WorkoutModel>>).data

            WorkoutsList(
                workouts = workouts,
                filterMode = filterMode,
                selectedType = selectedType,
                selectedDifficulty = selectedDifficulty,
                weeklyGoal = weeklyGoal,
                weeklyProgress = weeklyProgress,
                tips = tips,
                onFilterModeToggled = onFilterModeToggled,
                onTypeSelected = onTypeSelected,
                onDifficultySelected = onDifficultySelected,
                onWorkoutClick = onWorkoutClick,
            )
        },

        dataEmpty = {
            DataEmptyContent(
                primaryText = stringResource(R.string.workouts_state_empty),
                modifier = Modifier.fillMaxSize(),
            )
        },
    )
}

@Composable
private fun WorkoutsList(
    workouts: List<WorkoutModel>,
    filterMode: WorkoutFilterMode,
    selectedType: WorkoutType?,
    selectedDifficulty: Difficulty?,
    weeklyGoal: Int,
    weeklyProgress: Int,
    tips: List<TipModel>,
    onFilterModeToggled: () -> Unit,
    onTypeSelected: (WorkoutType?) -> Unit,
    onDifficultySelected: (Difficulty?) -> Unit,
    onWorkoutClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    val filteredWorkouts = remember(workouts, selectedType, selectedDifficulty) {
        workouts.filter { workout ->
            (selectedType == null || workout.type == selectedType) &&
                    (selectedDifficulty == null || workout.difficulty == selectedDifficulty)
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = dimens.screenPadding,
            bottom = dimens.screenPadding + LocalContentBottomPadding.current,
        ),
        verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
    ) {
        homeTopSection(
            tips = tips,
            weeklyGoal = weeklyGoal,
            weeklyProgress = weeklyProgress,
        )

        stickyHeader {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceContainer),
            ) {
                WorkoutFilterRow(
                    modifier = Modifier.padding(vertical = dimens.tightSpacing),
                    filterMode = filterMode,
                    selectedType = selectedType,
                    selectedDifficulty = selectedDifficulty,
                    onFilterModeToggled = onFilterModeToggled,
                    onTypeSelected = onTypeSelected,
                    onDifficultySelected = onDifficultySelected,
                )

                ControlDivider()
            }
        }

        if (filteredWorkouts.isEmpty()) {
            item {
                DataEmptyContent(
                    primaryText = stringResource(R.string.workout_filter_empty),
                    modifier = Modifier.fillParentMaxSize(),
                )
            }
        } else {
            items(filteredWorkouts, key = { workout -> workout.id }) { workout ->
                WorkoutCard(
                    workout = workout,
                    modifier = Modifier.padding(horizontal = dimens.screenPadding),
                    onClick = { onWorkoutClick(workout.id) },
                )
            }
        }
    }
}
