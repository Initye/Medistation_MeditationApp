package com.example.medistation.ui.pages

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.dataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.medistation.ui.meditationSelect.BubbleBackground
import com.example.medistation.ui.meditationSelect.Element
import com.example.medistation.ui.meditationSelect.ProfileElement
import com.example.medistation.ui.meditationSelect.meditations
import com.example.medistation.ui.theme.BackgroundPage
import com.example.medistation.ui.theme.itimFont
import com.example.medistation.viewModels.ProfileViewModel


@Composable
fun ProfilePage(navController: NavController, modifier: Modifier = Modifier) {
    val profileViewModel: ProfileViewModel = viewModel()
    Box (
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundPage)
    ){
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 100.dp, start = 10.dp, end = 10.dp)
                .windowInsetsPadding(WindowInsets.systemBars),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Medistation",
                fontSize = 40.sp,
                color = Color.White,
                fontFamily = itimFont, fontWeight = FontWeight.Normal
            )
            Spacer(modifier.padding(5.dp))
            Text(
                text = "My profile",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                color = Color.White,
                fontFamily = itimFont, fontWeight = FontWeight.Normal
            )
            Spacer(Modifier.height(40.dp))
            ProfileElement(title = "Total time:", description = profileViewModel.totalTimeText(
                LocalContext.current, timeKey = "totalMeditationTime") )
        }
    }
}

@Preview
@Composable
fun ProfilePagePreview() {
    ProfilePage(rememberNavController())
}