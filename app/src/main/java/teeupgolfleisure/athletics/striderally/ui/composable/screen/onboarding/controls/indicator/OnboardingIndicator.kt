package teeupgolfleisure.athletics.striderally.ui.composable.screen.onboarding.controls.indicator

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens

private val DotWidth = 8.dp
private val SelectedDotWidth = 24.dp
private val DotHeight = 8.dp

@Composable
fun OnboardingIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        repeat(pageCount) { page ->
            val isSelected = currentPage == page

            val width by animateDpAsState(
                targetValue = if (isSelected) SelectedDotWidth else DotWidth,
                label = "onboarding_worm_dot_width",
            )
            val color by animateColorAsState(
                targetValue = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.outlineVariant
                },
                label = "onboarding_worm_dot_color",
            )

            Box(
                modifier = Modifier
                    .height(DotHeight)
                    .width(width)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}
