package teeupgolfleisure.athletics.striderally.ui.composable.screen.onboarding.icon

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import teeupgolfleisure.athletics.striderally.ui.theme.imageBorder
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private val BackdropSize = 152.dp
private val InnerCircleSize = 112.dp
private val RingStrokeWidth = 2.dp
private val IconSize = 40.dp
private const val IconRotationIntervalMs = 1800L

@Composable
fun OnboardingIcon(
    icons: List<ImageVector>,
    modifier: Modifier = Modifier,
) {
    var currentIndex by remember(icons) { mutableIntStateOf(0) }

    LaunchedEffect(icons) {
        while (icons.size > 1) {
            delay(IconRotationIntervalMs.milliseconds)
            currentIndex = (currentIndex + 1) % icons.size
        }
    }

    Box(
        modifier = modifier
            .size(BackdropSize)
            .border(RingStrokeWidth, MaterialTheme.colorScheme.outlineVariant, CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(InnerCircleSize)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .imageBorder(CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Crossfade(targetState = icons[currentIndex], label = "onboarding_icon") { icon ->
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(IconSize),
                )
            }
        }
    }
}
