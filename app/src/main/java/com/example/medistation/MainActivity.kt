package com.example.medistation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults.contentWindowInsets
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
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
import com.example.medistation.ui.theme.MedistationTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "startPage", builder = {
                    composable("startPage") {
                        StartPage(navController)
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
