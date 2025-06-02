package com.example.medistation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medistation.ui.meditationSelect.meditationsPages.CalmMed
import com.example.medistation.ui.meditationSelect.meditationsPages.FocusMed
import com.example.medistation.ui.meditationSelect.meditationsPages.RainMed
import com.example.medistation.ui.meditationSelect.meditationsPages.RelaxMed
import com.example.medistation.ui.meditationSelect.meditationsPages.SleepMed
import com.example.medistation.ui.pages.MeditationPage
import com.example.medistation.ui.pages.ProfilePage
import com.example.medistation.ui.pages.StartPage
import com.example.medistation.viewModels.ProfileViewModel
import kotlinx.coroutines.delay
import java.util.prefs.Preferences

val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "startPage", builder = {

                    composable("startPage") {
                        StartPage()
                    }
                    composable("meditationPage") {
                        MeditationPage(navController)
                    }
                    composable("profilePage") {
                        ProfilePage(navController)
                    }
                    composable("relaxMed") {
                        RelaxMed(profileViewModel = viewModel())
                    }
                    composable("calmMed") {
                        CalmMed()
                    }
                    composable("rainMed") {
                        RainMed()
                    }
                    composable("focusMed") {
                        FocusMed()
                    }
                    composable("sleepMed") {
                        SleepMed()
                    }
                })
                //Start from startPage and go to meditationPage after delay
                LaunchedEffect(Unit) {
                    delay(4000)
                    navController.navigate("meditationPage") {
                        popUpTo("startPage") { inclusive = true }
                    }
            }
        }
    }
}
