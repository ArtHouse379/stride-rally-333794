package teeupgolfleisure.athletics.striderally.ui.composable.screen.home.workoutcard.shell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import teeupgolfleisure.athletics.striderally.ui.composable.screen.home.workoutcard.WorkoutCardInfo
import teeupgolfleisure.athletics.striderally.ui.composable.screen.shared.WorkoutImage
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens
import teeupgolfleisure.athletics.striderally.ui.theme.cardBorderStroke

private val ImageHeight = 160.dp

@Composable
fun WorkoutCardShell(
    imageUrl: String,
    info: WorkoutCardInfo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (WorkoutCardInfo) -> Unit,
) {
    val dimens = LocalDimens.current

    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(dimens.cardCornerRadius),
        border = cardBorderStroke(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column {
            WorkoutImage(
                imageUrl = imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ImageHeight),
            )

            Column(modifier = Modifier.padding(vertical = dimens.cardPadding)) {
                content(info)
            }
        }
    }
}
