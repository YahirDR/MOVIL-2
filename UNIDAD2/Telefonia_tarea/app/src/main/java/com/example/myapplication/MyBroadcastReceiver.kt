package com.example.myapplication
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

            if (state != null) {
                if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                    val phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
                    val savedNumber = getSavedNumber(context)
                    Toast.makeText(context, "Llamada entrante de: $phoneNumber", Toast.LENGTH_SHORT).show()
                    Toast.makeText(context, "Comparando numero entrante: $phoneNumber \n con:$savedNumber " , Toast.LENGTH_SHORT).show()
                    if (phoneNumber == savedNumber) {
                        Toast.makeText(context, "Mensaje enviado a $phoneNumber", Toast.LENGTH_SHORT).show()
                        enviarMensaje(context)
                    }
                }
            }
        }
    }

    private fun enviarMensaje(context: Context) {
        val smsManager = SmsManager.getDefault()
        val message = getSavedMessage(context)
        val number = getSavedNumber(context)
        smsManager.sendTextMessage(number, null, message, null, null)
    }

    private fun getSavedNumber(context: Context): String? {
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPrefs.getString("saved_number", null)
    }

    private fun getSavedMessage(context: Context): String? {
        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPrefs.getString("saved_message", null)
    }
}
