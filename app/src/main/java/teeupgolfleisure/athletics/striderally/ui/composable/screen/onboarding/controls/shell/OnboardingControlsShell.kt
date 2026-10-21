package teeupgolfleisure.athletics.striderally.ui.composable.screen.onboarding.controls.shell

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import teeupgolfleisure.athletics.striderally.R
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens

@Composable
fun OnboardingControlsShell(
    pageCount: Int,
    currentPage: Int,
    isLastPage: Boolean,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
    indicator: @Composable (pageCount: Int, currentPage: Int, modifier: Modifier) -> Unit,
    buttonLabel: @Composable (isLastPage: Boolean, currentPage: Int, pageCount: Int) -> String,
    button: @Composable (label: String, onClick: () -> Unit, modifier: Modifier) -> Unit,
) {
    val dimens = LocalDimens.current

    Column(modifier = modifier) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd,
        ) {
            if (!isLastPage) {
                TextButton(
                    onClick = onSkipClick,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                    ),
                ) {
                    Text(text = stringResource(R.string.skip_button_title))
                }
            }
        }

        indicator(
            pageCount,
            currentPage,
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = dimens.sectionGap),
        )

        button(
            buttonLabel(isLastPage, currentPage, pageCount),
            onNextClick,
            Modifier.fillMaxWidth(),
        )
    }
}
