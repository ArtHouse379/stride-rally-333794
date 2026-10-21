package teeupgolfleisure.athletics.striderally.ui.composable.screen.workout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import teeupgolfleisure.athletics.striderally.R
import teeupgolfleisure.athletics.striderally.data.model.WorkoutModel
import teeupgolfleisure.athletics.striderally.ui.composable.approot.LocalContentBottomPadding
import teeupgolfleisure.athletics.striderally.ui.composable.screen.shared.DataBasedContainer
import teeupgolfleisure.athletics.striderally.ui.composable.screen.shared.DataEmptyContent
import teeupgolfleisure.athletics.striderally.ui.composable.screen.workout.actionblock.WorkoutActionBlock
import teeupgolfleisure.athletics.striderally.ui.composable.screen.workout.exercisecard.content.ExerciseCardContent
import teeupgolfleisure.athletics.striderally.ui.composable.screen.workout.exercisecard.shell.ExerciseCard
import teeupgolfleisure.athletics.striderally.ui.composable.screen.workout.typedifficulty.WorkoutTypeDifficulty
import teeupgolfleisure.athletics.striderally.ui.state.DataUiState
import teeupgolfleisure.athletics.striderally.ui.state.ExercisePhase
import teeupgolfleisure.athletics.striderally.ui.state.ExerciseStatus
import teeupgolfleisure.athletics.striderally.ui.state.WorkoutUiState
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens
import teeupgolfleisure.athletics.striderally.ui.viewmodel.WorkoutViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SDPMEWorkoutScreen(
    workoutId: Int,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WorkoutViewModel = koinViewModel { parametersOf(workoutId) },
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DataBasedContainer(
        modifier = modifier.fillMaxSize(),
        dataState = uiState,

        dataPopulated = {
            val state = (uiState as DataUiState.Populated<WorkoutUiState>).data

            WorkoutScreenContent(
                state = state,
                onStartClick = viewModel::onStartWorkoutClick,
                onEndWorkoutConfirmed = viewModel::onEndWorkoutClick,
                onAdvanceConfirmed = viewModel::onAdvanceConfirmed,
                onNavigateBack = onNavigateBack,
            )
        },

        dataEmpty = {
            DataEmptyContent(
                primaryText = stringResource(R.string.workout_details_state_empty),
                modifier = Modifier.fillMaxSize(),
            )
        },
    )
}

@Composable
private fun WorkoutScreenContent(
    state: WorkoutUiState,
    onStartClick: () -> Unit,
    onEndWorkoutConfirmed: () -> Unit,
    onAdvanceConfirmed: (Int) -> Unit,
    onNavigateBack: () -> Unit,
) {
    var confirmIndex by remember { mutableStateOf<Int?>(null) }

    var confirmPhase by remember { mutableStateOf<ExercisePhase?>(null) }
    var showEndWorkoutDialog by remember { mutableStateOf(false) }
    var showCompletedDialog by remember { mutableStateOf(false) }

    WorkoutContent(
        state = state,
        onStartClick = onStartClick,
        onEndClick = { showEndWorkoutDialog = true },
        onExerciseClick = { index ->
            val item = state.exercises.getOrNull(index)
            if (item != null && item.isTimed) {
                confirmIndex = index
                confirmPhase = item.phase
            } else {
                onAdvanceConfirmed(index)
            }
        },
    )

    val pendingIndex = confirmIndex
    val pendingPhase = confirmPhase
    if (pendingIndex != null && pendingPhase != null) {
        val liveItem = state.exercises.getOrNull(pendingIndex)

        LaunchedEffect(liveItem?.status, liveItem?.phase) {
            if (liveItem == null || liveItem.status != ExerciseStatus.ACTIVE || liveItem.phase != pendingPhase) {
                confirmIndex = null
                confirmPhase = null
            }
        }

        if (liveItem != null && liveItem.status == ExerciseStatus.ACTIVE && liveItem.phase == pendingPhase) {
            AdvanceConfirmDialog(
                phase = pendingPhase,
                onConfirm = {
                    onAdvanceConfirmed(pendingIndex)
                    confirmIndex = null
                    confirmPhase = null
                },
                onDismiss = {
                    confirmIndex = null
                    confirmPhase = null
                },
            )
        }
    }

    if (showEndWorkoutDialog) {
        EndWorkoutConfirmDialog(
            onConfirm = {
                onEndWorkoutConfirmed()
                showEndWorkoutDialog = false
            },
            onDismiss = { showEndWorkoutDialog = false },
        )
    }

    LaunchedEffect(state.isFinished) {
        if (state.isFinished) {
            val allCompleted = state.exercises.all { it.status == ExerciseStatus.COMPLETED }
            if (allCompleted) {
                showCompletedDialog = true
            } else {
                onNavigateBack()
            }
        }
    }

    if (showCompletedDialog) {
        WorkoutCompletedDialog(
            onDismiss = {
                showCompletedDialog = false
                onNavigateBack()
            },
        )
    }
}

@Composable
private fun WorkoutContent(
    state: WorkoutUiState,
    onStartClick: () -> Unit,
    onEndClick: () -> Unit,
    onExerciseClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val isInProgress = state.hasStarted && !state.isFinished

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                start = dimens.screenPadding,
                end = dimens.screenPadding,
                top = dimens.screenPadding,
                bottom = if (state.isFinished) {
                    dimens.screenPadding + LocalContentBottomPadding.current
                } else {
                    dimens.screenPadding
                },
            ),
            verticalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
        ) {
            item {
                WorkoutHeader(workout = state.workout)
            }

            itemsIndexed(state.exercises) { index, item ->
                ExerciseCard(
                    number = index + 1,
                    item = item,
                    onClick = { onExerciseClick(index) },
                ) {
                    ExerciseCardContent(item = item)
                }
            }
        }

        if (!state.isFinished) {
            WorkoutActionBlock(
                isInProgress = isInProgress,
                onClick = if (isInProgress) onEndClick else onStartClick,
            )
        }
    }
}

@Composable
private fun WorkoutHeader(
    workout: WorkoutModel,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        Text(
            text = workout.name,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        WorkoutTypeDifficulty(
            type = workout.type,
            difficulty = workout.difficulty,
        )
    }
}

@Composable
private fun AdvanceConfirmDialog(
    phase: ExercisePhase,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    val titleRes = when (phase) {
        ExercisePhase.WORK -> R.string.exercise_finish_set_dialog_title
        ExercisePhase.REST -> R.string.exercise_skip_rest_dialog_title
    }
    val messageRes = when (phase) {
        ExercisePhase.WORK -> R.string.exercise_finish_set_dialog_message
        ExercisePhase.REST -> R.string.exercise_skip_rest_dialog_message
    }
    val confirmRes = when (phase) {
        ExercisePhase.WORK -> R.string.exercise_finish_set_dialog_confirm
        ExercisePhase.REST -> R.string.exercise_skip_rest_dialog_confirm
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(titleRes)) },
        text = { Text(stringResource(messageRes)) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(stringResource(confirmRes))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.exercise_dialog_cancel))
            }
        },
    )
}

@Composable
private fun EndWorkoutConfirmDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.end_workout_dialog_title)) },
        text = { Text(stringResource(R.string.end_workout_dialog_message)) },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(stringResource(R.string.end_workout_dialog_confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.exercise_dialog_cancel))
            }
        },
    )
}

@Composable
private fun WorkoutCompletedDialog(
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        icon = {
            Icon(
                imageVector = Icons.Filled.EmojiEvents,
                contentDescription = null,
            )
        },
        title = { Text(stringResource(R.string.workout_finished_title)) },
        text = { Text(stringResource(R.string.workout_finished_description)) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.workout_completed_dialog_confirm))
            }
        },
    )
}
