package com.example.sr_blooms.viewmodel

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BatteryStatusViewModel(application: Application) : AndroidViewModel(application) {

    val batteryLevel = mutableStateOf(0)
    val isCharging = mutableStateOf(false)

    private val batteryReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                val level = it.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = it.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                batteryLevel.value = if (level >= 0 && scale > 0) (level * 100 / scale) else 0

                val status = it.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                isCharging.value = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
            }
        }
    }

    init {
        viewModelScope.launch {
            val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            application.registerReceiver(batteryReceiver, filter)
        }
    }

    override fun onCleared() {
        getApplication<Application>().unregisterReceiver(batteryReceiver)
        super.onCleared()
    }
}
