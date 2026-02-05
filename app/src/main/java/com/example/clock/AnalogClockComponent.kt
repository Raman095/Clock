package com.example.clock

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.min
import android.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun AnalogClockComponent(
    hour: Int,
    minute: Int,
    second: Int
) {
    Box(
        modifier = Modifier.fillMaxSize(fraction = 0.7f)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val diameter = min(size.width, size.height) * 0.9f
            val radius = diameter / 2

            val textPaint = Paint().apply {
                color = android.graphics.Color.WHITE
                textSize = 20.dp.toPx()
                textAlign = Paint.Align.CENTER
                isAntiAlias = true
            }

            val textRadius = radius * 0.75f

            for (i in 1..12) {

                val angleInDegrees = i * 30f - 90f
                val angleInRadians = Math.toRadians(angleInDegrees.toDouble())

                val x = center.x + textRadius * kotlin.math.cos(angleInRadians).toFloat()
                val y = center.y + textRadius * kotlin.math.sin(angleInRadians).toFloat() + textPaint.textSize / 3   // vertical centering tweak

                drawContext.canvas.nativeCanvas.drawText(
                    i.toString(),
                    x,
                    y,
                    textPaint
                )
            }


            repeat(60) { i ->

                val isHourMark = i % 5 == 0

                val lineLength = if (isHourMark) {
                    radius / 13.6f      // hour lines (longer)
                } else {
                    radius / 25f        // minute lines (shorter)
                }

                val stroke = if (isHourMark) {
                    2.5.dp.toPx()
                } else {
                    2.dp.toPx()
                }

                val tickColor = if (isHourMark) {
                    Color.White
                } else {
                    Color.DarkGray   // grey minute ticks
                }

                val start = center - Offset(0f, radius)
                val end = start + Offset(0f, lineLength)

                rotate(i * 6f) { // 360 / 60 = 6°
                    drawLine(
                        color = tickColor,
                        start = start,
                        end = end,
                        strokeWidth = stroke,
                        cap = StrokeCap.Round
                    )
                }
            }

            val secondRatio = second / 60f
            val minuteRatio = minute / 60f
            val hourRatio = (hour % 12 + minute / 60f) / 12f

            rotate(hourRatio * 360, center) {
                drawLine(
                    color = Color.White,
                    start = center - Offset(0f, radius * 0.4f),
                    end = center ,
                    strokeWidth = 6.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }

            rotate(minuteRatio * 360, center) {
                drawLine(
                    color = Color.White,
                    start = center - Offset(0f, radius * 0.6f),
                    end = center ,
                    strokeWidth = 6.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }

            drawCircle(
                color = Color.White,
                radius = 7.dp.toPx(),
                center = center
            )

            rotate(secondRatio * 360, center) {
                drawLine(
                    color = Color(0xFFFF5722),
                    start = center - Offset(0f, radius * 0.8f),
                    end = center + Offset(0f, radius * 0.1f),
                    strokeWidth = 3.dp.toPx(),
                    cap = StrokeCap.Round
                )
            }

            drawCircle(
                color = Color(0xFFFF5722),
                radius = 4.dp.toPx(),
                center = center
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnalogClockComponentPreview() {
    AnalogClockComponent(
        hour = 0,
        minute = 0,
        second = 0
    )
}