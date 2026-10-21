package teeupgolfleisure.athletics.striderally.ui.composable.screen.onboarding.controls.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens

private val ButtonIconSize = 20.dp
private val ButtonHeight = 52.dp

@Composable
fun OnboardingButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Button(
        onClick = onClick,
        modifier = modifier.height(ButtonHeight),
        shape = RoundedCornerShape(dimens.controlCornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
    ) {
        Text(text = label)

        Spacer(modifier = Modifier.width(dimens.itemSpacing))

        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(ButtonIconSize),
        )
    }
}
