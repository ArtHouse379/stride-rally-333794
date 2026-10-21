package teeupgolfleisure.athletics.striderally.ui.composable.screen.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import teeupgolfleisure.athletics.striderally.R
import teeupgolfleisure.athletics.striderally.ui.composable.approot.LocalContentBottomPadding
import teeupgolfleisure.athletics.striderally.ui.composable.screen.profile.sections.ProfileSections
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens
import teeupgolfleisure.athletics.striderally.ui.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SDPMEProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val weeklyGoalText by viewModel.weeklyGoalText.collectAsStateWithLifecycle()
    val heightText by viewModel.heightText.collectAsStateWithLifecycle()
    val weightText by viewModel.weightText.collectAsStateWithLifecycle()

    ProfileContent(
        weeklyGoalText = weeklyGoalText,
        heightText = heightText,
        weightText = weightText,
        onWeeklyGoalChanged = viewModel::onWeeklyGoalChanged,
        onHeightChanged = viewModel::onHeightChanged,
        onWeightChanged = viewModel::onWeightChanged,
        onResetDataConfirmed = viewModel::onResetDataConfirmed,
        modifier = modifier,
    )
}

@Composable
private fun ProfileContent(
    weeklyGoalText: String,
    heightText: String,
    weightText: String,
    onWeeklyGoalChanged: (String) -> Unit,
    onHeightChanged: (String) -> Unit,
    onWeightChanged: (String) -> Unit,
    onResetDataConfirmed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    var showResetDialog by remember { mutableStateOf(false) }

    ProfileSections(
        weeklyGoalText = weeklyGoalText,
        heightText = heightText,
        weightText = weightText,
        onWeeklyGoalChanged = onWeeklyGoalChanged,
        onHeightChanged = onHeightChanged,
        onWeightChanged = onWeightChanged,
        onResetClick = { showResetDialog = true },
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                start = dimens.screenPadding,
                end = dimens.screenPadding,
                top = dimens.screenPadding,
                bottom = dimens.screenPadding + LocalContentBottomPadding.current,
            ),
    )

    if (showResetDialog) {
        ResetDataConfirmDialog(
            onConfirm = {
                onResetDataConfirmed()
                showResetDialog = false
            },
            onDismiss = { showResetDialog = false },
        )
    }
}

@Composable
private fun ResetDataConfirmDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.profile_screen_reset_data_dialog_title)) },
        text = { Text(stringResource(R.string.profile_screen_reset_data_dialog_message)) },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error,
                ),
            ) {
                Text(stringResource(R.string.profile_screen_reset_data_dialog_confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.exercise_dialog_cancel))
            }
        },
    )
}
