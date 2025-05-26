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
import androidx.navigation.NavController
import com.example.medistation.R
import com.example.medistation.ui.theme.BackgroundPage
import com.example.medistation.ui.theme.itimFont


@Composable
fun StartPage(navController: NavController, modifier: Modifier = Modifier) {
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
                text = "Time to\nrelax",
                textAlign = TextAlign.Center,
                fontSize = 60.sp,
                color = Color.White,
                fontFamily = itimFont, fontWeight = FontWeight.Bold
            )
            Spacer(modifier.weight(1f))
            Box(
                modifier = modifier
                    .height(400.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxSize().zIndex(2f),
                    painter = painterResource(id = R.drawable.meditate),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds
                )
                Image(
                    painter = painterResource(id = R.drawable.bubbles),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview
@Composable
fun StartPagePreview() {
    StartPage(
        navController = TODO(),
        modifier = TODO()
    )
}