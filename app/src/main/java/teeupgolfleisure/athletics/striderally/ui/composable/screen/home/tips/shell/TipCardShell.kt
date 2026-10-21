package teeupgolfleisure.athletics.striderally.ui.composable.screen.home.tips.shell

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import teeupgolfleisure.athletics.striderally.data.model.TipModel
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens

private val DotSize = 6.dp

@Composable
fun TipCardShell(
    tip: TipModel,
    tipCount: Int,
    selectedIndex: Int,
    onAdvance: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (TipModel) -> Unit,
) {
    val dimens = LocalDimens.current
    val gradient = Brush.horizontalGradient(
        listOf(
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.tertiaryContainer,
        ),
    )

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(dimens.cardCornerRadius))
            .background(gradient)
            .clickable(onClick = onAdvance)
            .padding(dimens.cardPadding),
    ) {
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSecondaryContainer) {
            AnimatedContent(
                targetState = tip,
                transitionSpec = {
                    (slideInHorizontally(initialOffsetX = { width -> width }) + fadeIn())
                        .togetherWith(slideOutHorizontally(targetOffsetX = { width -> -width }) + fadeOut())
                },
                label = "tip",
            ) { animatedTip ->
                content(animatedTip)
            }

            if (tipCount > 1) {
                Spacer(modifier = Modifier.height(dimens.itemSpacing))

                PageDots(
                    count = tipCount,
                    selectedIndex = selectedIndex,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
private fun PageDots(
    count: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val dotColor = LocalContentColor.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.End,
    ) {
        repeat(count) { index ->
            if (index > 0) {
                Spacer(modifier = Modifier.width(dimens.tightSpacing))
            }

            Box(
                modifier = Modifier
                    .size(DotSize)
                    .background(
                        color = if (index == selectedIndex) {
                            dotColor
                        } else {
                            dotColor.copy(alpha = 0.35f)
                        },
                        shape = CircleShape,
                    ),
            )
        }
    }
}
