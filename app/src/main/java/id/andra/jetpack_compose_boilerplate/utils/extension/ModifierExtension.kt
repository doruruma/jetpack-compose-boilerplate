package id.andra.jetpack_compose_boilerplate.utils.extension

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import co.jasatama.courier.presentation.theme.Neutral10
import co.jasatama.courier.presentation.theme.ShimmerColor
import co.jasatama.courier.presentation.theme.space
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

@Composable
fun Modifier.placeholderShimmer(
    isVisible: Boolean
) = this
    .clip(RoundedCornerShape(space.x1))
    .placeholder(
        visible = isVisible,
        highlight = PlaceholderHighlight.shimmer(Neutral10),
        color = ShimmerColor,
    )

@Composable
fun Modifier.placeholderShimmerShapes(
    isVisible: () -> Boolean
) = this
    .fillMaxWidth()
    .clip(RoundedCornerShape(space.smaller))
    .placeholder(
        visible = isVisible(),
        highlight = PlaceholderHighlight.shimmer(Neutral10),
        color = ShimmerColor,
    )

fun Modifier.dashedBorder(width: Dp, radius: Dp, color: Color) =
    drawBehind {
        drawIntoCanvas {
            val paint = Paint()
                .apply {
                    strokeWidth = width.toPx()
                    this.color = color
                    style = PaintingStyle.Stroke
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                }
            it.drawRoundRect(
                width.toPx(),
                width.toPx(),
                size.width - width.toPx(),
                size.height - width.toPx(),
                radius.toPx(),
                radius.toPx(),
                paint
            )
        }
    }