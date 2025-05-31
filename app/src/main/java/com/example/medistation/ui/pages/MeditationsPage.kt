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
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.medistation.ui.meditationSelect.Element
import com.example.medistation.ui.meditationSelect.meditations
import com.example.medistation.ui.theme.BackgroundPage
import com.example.medistation.ui.theme.itimFont
import androidx.compose.foundation.lazy.items
import androidx.navigation.compose.rememberNavController
import com.example.medistation.ui.meditationSelect.BubbleBackground



@Composable
fun MeditationPage(navController: NavController, modifier: Modifier = Modifier) {
    Box (
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPage)
    ){
        BubbleBackground()
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
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                color = Color.White,
                fontFamily = itimFont, fontWeight = FontWeight.Normal
            )
            Spacer(Modifier.height(24.dp))

            LazyColumn {
                items(meditations) { item ->
                    Element(
                        title = item.title,
                        image = item.image,
                        onClick = {
                            navController.navigate(item.destination)
                        }
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun MeditationPagePreview() {
    MeditationPage(rememberNavController())
}