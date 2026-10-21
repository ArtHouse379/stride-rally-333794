package teeupgolfleisure.athletics.striderally.ui.composable.approot.bottombar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import teeupgolfleisure.athletics.striderally.ui.composable.approot.bottombar.BottomNavItem
import teeupgolfleisure.athletics.striderally.ui.composable.approot.bottombar.isSelectedDestination
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens
import teeupgolfleisure.athletics.striderally.ui.theme.controlBorderStroke

private val BarHeight = 64.dp
private val IconSize = 20.dp
private val BarElevation = 2.dp

@Composable
fun AppBottomBar(
    currentDestination: NavDestination?,
    navigationItems: List<BottomNavItem>,
    onNavigateToRoute: (BottomNavItem) -> Unit,
) {
    val dimens = LocalDimens.current
    val shape = RoundedCornerShape(dimens.cardCornerRadius)
    val border = controlBorderStroke()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(
                start = dimens.screenPadding,
                end = dimens.screenPadding,
                bottom = dimens.contentSpacing,
            )
    ) {
        NavigationBar(
            modifier = Modifier
                .height(BarHeight)
                .shadow(elevation = BarElevation, shape = shape, clip = true)
                .then(border?.let { Modifier.border(it, shape) } ?: Modifier),
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
            windowInsets = WindowInsets(0, 0, 0, 0),
        ) {
            navigationItems.forEach { item ->
                NavigationBarItem(
                    selected = isSelectedDestination(currentDestination, item.route),
                    onClick = { onNavigateToRoute(item) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier.size(IconSize)
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(item.titleRes),
                            style = MaterialTheme.typography.labelSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.secondary,
                        selectedIconColor = MaterialTheme.colorScheme.onSecondary,
                        selectedTextColor = MaterialTheme.colorScheme.secondary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                )
            }
        }
    }
}
