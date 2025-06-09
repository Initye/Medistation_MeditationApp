package com.example.medistation.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.medistation.ui.meditationSelect.BubbleBackground
import com.example.medistation.ui.meditationSelect.Element
import com.example.medistation.ui.meditationSelect.meditations
import com.example.medistation.ui.theme.BackgroundPage
import com.example.medistation.ui.theme.headerDescription
import com.example.medistation.ui.theme.headerTitle
import com.example.medistation.ui.theme.iconSize
import com.example.medistation.ui.theme.itimFont

@Composable
fun MeditationPage(navController: NavController, modifier: Modifier = Modifier) {
    Box (
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPage)
    ){
        BubbleBackground()

        Row(
            modifier = modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.systemBars),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    navController.navigate("profilePage")
                },
                modifier = modifier
                    .padding(end = 12.dp),
                ) {
                Icon(
                    Icons.Rounded.Person,
                    contentDescription = "User profile",
                    tint = Color.White,
                    modifier = modifier
                        .size(iconSize)
                )
            }
        }



        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
                .windowInsetsPadding(WindowInsets.systemBars),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Medistation",
                fontSize = headerTitle,
                color = Color.White,
                fontFamily = itimFont, fontWeight = FontWeight.Normal
            )
            Spacer(modifier.padding(10.dp))
            Text(
                text = "Explore\nmeditations",
                textAlign = TextAlign.Center,
                fontSize = headerDescription,
                color = Color.White,
                fontFamily = itimFont, fontWeight = FontWeight.Normal
            )
            Spacer(Modifier.height(40.dp))

            LazyColumn(
                modifier = modifier
                    .padding(start = 10.dp, end = 10.dp)
            ) {
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