package com.example.medistation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medistation.ui.meditationSelect.meditationsPages.CalmMed
import com.example.medistation.ui.meditationSelect.meditationsPages.FocusMed
import com.example.medistation.ui.meditationSelect.meditationsPages.RainMed
import com.example.medistation.ui.meditationSelect.meditationsPages.RelaxMed
import com.example.medistation.ui.meditationSelect.meditationsPages.SleepMed
import com.example.medistation.ui.pages.MeditationPage
import com.example.medistation.ui.pages.StartPage
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
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
                    composable("relaxMed") {
                        RelaxMed()
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
