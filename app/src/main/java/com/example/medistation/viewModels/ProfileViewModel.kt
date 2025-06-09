package com.example.medistation.viewModels

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.medistation.dataStore
import kotlinx.coroutines.flow.first
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import com.example.medistation.R
import kotlinx.coroutines.launch
import kotlin.time.*


class ProfileViewModel : ViewModel() {
    private val _totalMeditationTime = mutableLongStateOf(0L)
    val totalMeditationTime: Long
        get() = _totalMeditationTime.longValue


    suspend fun addMeditationTime(seconds: Long, context: Context) {
        val currentTotal = getTotalTime(context, "totalMeditationTime") ?: 0L
        val newTotal = currentTotal + seconds //To avoid the bug with resetting the value to 0
        saveTotalTime(context, time = "totalMeditationTime", value = newTotal)
        _totalMeditationTime.longValue = newTotal
    }
    // Saving total time to dataStore
    suspend fun saveTotalTime(context: Context, time: String, value: Long) {
        val dataStoreKey = longPreferencesKey(time)
        context.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }
    // Getting total time from dataStore
    suspend fun getTotalTime(context: Context, time: String): Long? {
        val dataStoreKey = longPreferencesKey(time)
        val preferences = context.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    // Text composable for totalTime (displayed in userProfile)
    @Composable
    fun totalTimeText(context: Context, timeKey: String): String {
        var totalTime by remember { mutableStateOf<Long?>(0) }
        LaunchedEffect(timeKey) {
            totalTime = getTotalTime(context, timeKey)
        }

       return totalTime?.let { seconds ->
           val hours = seconds / 3600
           val minutes = (seconds % 3600) / 60
           val second = seconds % 60
           "${hours}h:${minutes}m:${second}s"
       }
           ?: "None"
    }

    // Sound composable
    // Saving current song to dataStore
    suspend fun saveCurrentSong(context: Context, song: String) {
        val dataStoreKey = stringPreferencesKey("currentSelectedSong")
        context.dataStore.edit { settings ->
            settings[dataStoreKey] = song
        }
    }

    // Getting current song from dataStore
    suspend fun getCurrentSong(context: Context): String? {
        val dataStoreKey = stringPreferencesKey("currentSelectedSong")
        val preferences = context.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    suspend fun loadSavedSong(context: Context) {
        val savedSong = getCurrentSong(context)
        currentSelectedSong.value = savedSong ?: "null"
    }

    val currentSelectedSong = mutableStateOf("null")
    fun getMusicType(music: String, context: Context) {
        viewModelScope.launch {
            saveCurrentSong(context, music)
        }
        currentSelectedSong.value = music
        Log.d("Type","changed ${music}")
    }

    @Composable
    fun BackgroundMusic() {
        val context = LocalContext.current
        val currentMusic by currentSelectedSong

        Log.d("BackgroundMusic", "Current music: $currentMusic")

        DisposableEffect(currentMusic) {
            val resourceId = when(currentMusic) {
                "lofi" -> R.raw.lofi
                "meditation" -> R.raw.meditation
                "rain" -> R.raw.rain
                else -> null
            }
            Log.d("BackgroundMusic", "Resource ID: $resourceId")
            val mediaPlayer = resourceId?.let {
                MediaPlayer.create(context, it).apply { start() }
            }
            onDispose {
                mediaPlayer?.stop()
                mediaPlayer?.release()
            }
        }
    }
}
