package com.example.myapplication

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var stateSwitch: Boolean = false
    val br: BroadcastReceiver = MyBroadcastReceiver()
    lateinit var swt: Switch
    lateinit var btn: Button
    val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
        addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        //addAction("android.intent.action.PHONE_STATE")
    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val configButton = findViewById<Button>(R.id.button_configuration)
        configButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        swt = findViewById(R.id.switchET)

        stateSwitch = isMyServiceRunning(ServicePhoneStateWithBroadcastReceiver::class.java)
        swt.isChecked = stateSwitch

        swt.setOnClickListener {
            stateSwitch = swt.isChecked()
            if (stateSwitch) {
                val callService = Intent(this, ServicePhoneStateWithBroadcastReceiver::class.java)
                try {
                    startService(callService)
                    Toast.makeText(this, "Escuchando", Toast.LENGTH_SHORT).show();
                } catch (ex: Exception) {
                    Log.d(packageName, ex.toString())
                }
            } else {
                stopService(Intent(this, ServicePhoneStateWithBroadcastReceiver::class.java))
                Toast.makeText(this, "Ya no estoy escuchando", Toast.LENGTH_SHORT).show()
            }
        }
    }




}