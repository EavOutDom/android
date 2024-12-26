package com.example.androidbasic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings

class AirplaneModeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
//            val isAirplaneModeEnabled = intent?.getBooleanExtra("state", false) ?: return
//            if (isAirplaneModeEnabled) {
//
//            } else {
//                // Airplane mode is disabled
//            }
            val isAirplaneModeEnabled = Settings.Global.getInt(
                context?.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON,
                0
            )
            println("Airplane mode is enabled: $isAirplaneModeEnabled")
        }
    }

}