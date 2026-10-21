package teeupgolfleisure.athletics.striderally.ui.composable.screen.onboarding.controls

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import teeupgolfleisure.athletics.striderally.ui.composable.screen.onboarding.controls.button.OnboardingButton
import teeupgolfleisure.athletics.striderally.ui.composable.screen.onboarding.controls.indicator.OnboardingIndicator
import teeupgolfleisure.athletics.striderally.ui.composable.screen.onboarding.controls.label.onboardingButtonLabel
import teeupgolfleisure.athletics.striderally.ui.composable.screen.onboarding.controls.shell.OnboardingControlsShell

@Composable
fun OnboardingControls(
    pageCount: Int,
    currentPage: Int,
    isLastPage: Boolean,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OnboardingControlsShell(
        pageCount = pageCount,
        currentPage = currentPage,
        isLastPage = isLastPage,
        onSkipClick = onSkipClick,
        onNextClick = onNextClick,
        modifier = modifier,
        indicator = { pc, cp, indicatorModifier -> OnboardingIndicator(pc, cp, indicatorModifier) },
        buttonLabel = { last, cp, pc -> onboardingButtonLabel(last, cp, pc) },
        button = { label, onClick, buttonModifier ->
            OnboardingButton(
                label,
                onClick,
                buttonModifier
            )
        },
    )
}
