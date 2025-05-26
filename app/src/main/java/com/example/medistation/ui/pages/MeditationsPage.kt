package com.example.medistation.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.medistation.R
import com.example.medistation.ui.theme.BackgroundPage
import com.example.medistation.ui.theme.itimFont


@Composable
fun MeditationPage(modifier: Modifier = Modifier) {
    Box (
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPage)
    ){
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 150.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Medistation",
                fontSize = 40.sp,
                color = Color.White,
                fontFamily = itimFont, fontWeight = FontWeight.Normal
            )
            Spacer(modifier.padding(20.dp))
            Text(
                text = "Explore\nmeditations",
                fontSize = 40.sp,
                color = Color.White,
                fontFamily = itimFont, fontWeight = FontWeight.Normal
            )
            //Place to implement buttons for selection of meditation
        }
    }
}

@Preview
@Composable
fun MeditationPagePreview() {
    MeditationPage()
}