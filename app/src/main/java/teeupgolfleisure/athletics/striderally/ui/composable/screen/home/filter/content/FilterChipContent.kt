package teeupgolfleisure.athletics.striderally.ui.composable.screen.home.filter.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import teeupgolfleisure.athletics.striderally.ui.composable.screen.home.filter.FilterChipItem
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens

private val SelectionDotSize = 8.dp
private val SelectionDotBorderWidth = 1.dp

@Composable
fun FilterChipContent(item: FilterChipItem, modifier: Modifier = Modifier) {
    val dimens = LocalDimens.current

    FilterChip(
        selected = item.selected,
        onClick = item.onClick,
        label = { Text(item.label) },
        leadingIcon = { SelectionDot(selected = item.selected) },
        shape = RoundedCornerShape(dimens.controlCornerRadius),
        modifier = modifier,
    )
}

@Composable
private fun SelectionDot(
    selected: Boolean,
    modifier: Modifier = Modifier,
) {
    val color = LocalContentColor.current

    Box(
        modifier = modifier.size(FilterChipDefaults.IconSize),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(SelectionDotSize)
                .then(
                    if (selected) {
                        Modifier.background(color = color, shape = CircleShape)
                    } else {
                        Modifier.border(
                            width = SelectionDotBorderWidth,
                            color = color,
                            shape = CircleShape
                        )
                    }
                ),
        )
    }
}
