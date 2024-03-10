package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.telephony.TelephonyManager

class ServicePhoneStateWithBroadcastReceiver : Service() {
    val br : MyBroadcastReceiver = MyBroadcastReceiver()
    val iFilter : IntentFilter = IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        registerReceiver(br, iFilter )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }

}