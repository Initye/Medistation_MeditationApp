package com.example.medistation.ui.meditationSelect.meditationsPages

import android.media.MediaPlayer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medistation.R
import com.example.medistation.ui.theme.BackgroundPage
import com.example.medistation.ui.theme.Purple80
import com.example.medistation.ui.theme.PurpleGrey70
import com.example.medistation.ui.theme.itimFont
import com.example.medistation.viewModels.ProfileViewModel
import kotlinx.coroutines.delay

@Composable
fun RainMed(modifier: Modifier = Modifier, profileViewModel: ProfileViewModel) {
    var visible by remember { mutableStateOf(false) }
    var order by remember { mutableStateOf("") }
    //How long to inhale
    val inhaleTime = tween<Float>(durationMillis = 5000)
    val inhaleTimeExpand = tween<IntSize>(durationMillis = 5000)
    //How long to exhale
    val exhaleTime = tween<Float>(durationMillis = 5000)
    val exhaleTimeShrink = tween<IntSize>(durationMillis = 5000)
    val context = LocalContext.current

    DisposableEffect(Unit) { //The sounds stops once user navigates away (or the composable is removed from the screen)
        val mediaPlayer = MediaPlayer.create(context, R.raw.rain)
        mediaPlayer.start()

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    LaunchedEffect(Unit) {
        repeat(18) { //2min
            visible = true
            order = "Inhale"
            delay(5000)
            delay(100) //To reset time between animations so it doesn't break it
            order = "Exhale"
            visible = false
            delay(5000)
            profileViewModel.addMeditationTime(10L, context)
            delay(100)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPage),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier
            .size(50.dp),
//            .zIndex(),
            onDraw =  {
                drawCircle(color = Color.White)
            })
        AnimatedVisibility(
            visible = visible,

            enter =
            scaleIn(transformOrigin = TransformOrigin(0f, 0f), animationSpec = inhaleTime) +
                    fadeIn(animationSpec = inhaleTime) +
                    expandIn(expandFrom = Alignment.TopStart, animationSpec = inhaleTimeExpand),

            exit =
            scaleOut(transformOrigin = TransformOrigin(0f, 0f), animationSpec = exhaleTime) +
                    fadeOut(animationSpec = exhaleTime ) +
                    shrinkOut(shrinkTowards = Alignment.TopStart, animationSpec = exhaleTimeShrink))
        {
            Canvas(modifier = modifier
                .size(300.dp)
                .border(width = 2.dp, color = Purple80, shape = CircleShape),
                onDraw =  {
                    drawCircle(color = PurpleGrey70)
                })
        }
        Text(
            text = order,
            fontSize = 36.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 60.dp),
            fontFamily = itimFont, fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
@Preview
fun PreviewRainMed() {
    RainMed(profileViewModel = viewModel())
}