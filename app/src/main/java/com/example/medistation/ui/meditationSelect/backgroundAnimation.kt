package com.example.medistation.ui.meditationSelect

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.roundToIntSize
import com.example.medistation.ui.theme.bubblesElement
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun BubbleBackground(
) {
    var visible by remember { mutableStateOf(List(10) { false }) }
    var x by remember { mutableStateOf(List(10) { 0f }) }
    var y by remember { mutableStateOf(List(10) { 0f }) }
    var canvasSize by remember { mutableStateOf(IntSize.Zero) }
    var sizes by remember { mutableStateOf(List(10) { 0f }) }

    LaunchedEffect(Unit) {
        while (true) {
            if (canvasSize != IntSize.Zero) {
                x = List(10) { Random.nextFloat() * canvasSize.width }
                y = List(10) { Random.nextFloat() * canvasSize.height }
                sizes = List(10) { Random.nextFloat() * 50f + 10f }
                visible = List(10) { true }
                delay(2000)
                visible = List(10) { false }
            }
        }
    }
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            canvasSize = size.roundToIntSize()
            visible.forEachIndexed { index, isVisible ->
                    drawCircle(
                        color = bubblesElement,
                        center = Offset(x[index],y[index]),
                        radius = sizes[index]
                    )
                }
        }
}



@Preview
@Composable
fun PreviewBubbles(){
    BubbleBackground()
}