package teeupgolfleisure.athletics.striderally.ui.composable.screen.workout.actionblock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import teeupgolfleisure.athletics.striderally.R
import teeupgolfleisure.athletics.striderally.ui.composable.approot.LocalContentBottomPadding
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens

private val ActionButtonHeight = 52.dp

@Composable
fun WorkoutActionBlock(
    isInProgress: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            .padding(
                start = dimens.cardPadding,
                end = dimens.cardPadding,
                top = dimens.cardPadding,
                bottom = dimens.cardPadding + LocalContentBottomPadding.current,
            ),
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(ActionButtonHeight),
            shape = RoundedCornerShape(dimens.controlCornerRadius),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
            ),
            colors = if (isInProgress) {
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                )
            } else {
                ButtonDefaults.buttonColors()
            },
        ) {
            Text(
                stringResource(
                    if (isInProgress) R.string.end_workout_button_title else R.string.start_workout_button_title
                )
            )
        }
    }
}
