package teeupgolfleisure.athletics.striderally.ui.composable.screen.home.tips.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import teeupgolfleisure.athletics.striderally.R
import teeupgolfleisure.athletics.striderally.data.model.TipModel
import teeupgolfleisure.athletics.striderally.ui.theme.LocalDimens

private const val MessageMinLines = 3

@Composable
fun TipCardContent(tip: TipModel) {
    val dimens = LocalDimens.current
    val contentColor = LocalContentColor.current

    Column {
        Text(
            text = stringResource(R.string.home_tip_eyebrow_label).uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.5.sp),
            color = contentColor,
        )

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        Text(
            text = tip.title,
            style = MaterialTheme.typography.titleSmall,
            color = contentColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(dimens.tightSpacing))

        Text(
            text = tip.message,
            style = MaterialTheme.typography.bodySmall,
            color = contentColor.copy(alpha = 0.75f),
            minLines = MessageMinLines,
        )
    }
}
