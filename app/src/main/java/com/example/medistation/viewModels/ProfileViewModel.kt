package com.example.medistation.viewModels

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ProfileViewModel : ViewModel() {
    private val _totalMeditationTime = mutableLongStateOf(0L)
    val totalMeditationTime: Long
        get() = _totalMeditationTime.longValue


    suspend fun addMeditationTime(seconds: Long, context: Context) {
        val currentTotal = getTotalTime(context, "totalMeditationTime") ?: 0L
        val newTotal = currentTotal + seconds
        saveTotalTime(context, time = "totalMeditationTime", value = newTotal)
        _totalMeditationTime.longValue = newTotal
    }

    suspend fun saveTotalTime(context: Context, time: String, value: Long) {
        val dataStoreKey = longPreferencesKey(time)
        context.dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun getTotalTime(context: Context, time: String): Long? {
        val dataStoreKey = longPreferencesKey(time)
        val preferences = context.dataStore.data.first()
        return preferences[dataStoreKey]
    }

    @Composable
    fun totalTimeText(context: Context, timeKey: String): String {
        var totalTime by remember { mutableStateOf<Long?>(0) }
        LaunchedEffect(timeKey) {
            totalTime = getTotalTime(context, timeKey)
        }

       return totalTime.toString()
    }
}

