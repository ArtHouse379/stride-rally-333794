package teeupgolfleisure.athletics.striderally.ui.composable.screen.home.layout

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import teeupgolfleisure.athletics.striderally.data.model.TipModel
import teeupgolfleisure.athletics.striderally.ui.composable.screen.home.goalcard.WeeklyGoalCard
import teeupgolfleisure.athletics.striderally.ui.composable.screen.home.tips.TipCard
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens

fun LazyListScope.homeTopSection(
    tips: List<TipModel>,
    weeklyGoal: Int,
    weeklyProgress: Int,
) {
    if (tips.isNotEmpty()) {
        item {
            val dimens = LocalDimens.current
            TipCard(
                tips = tips,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.screenPadding),
            )
        }
    }

    item {
        val dimens = LocalDimens.current
        WeeklyGoalCard(
            weeklyGoal = weeklyGoal,
            completed = weeklyProgress,
            modifier = Modifier.padding(horizontal = dimens.screenPadding),
        )
    }
}
