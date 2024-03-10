package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val button: Button = findViewById(R.id.button_save)
        button.setOnClickListener {
            val number: EditText = findViewById(R.id.edit_text_phone_number)
            val message: EditText = findViewById(R.id.edit_text_messaje)

            val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("saved_number", number.text.toString())
            editor.putString("saved_message", message.text.toString())
            editor.apply()

            Toast.makeText(this, "Datos Guardados \n Numero: ${number.text} \n Mensaje: ${message.text}", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MyBroadcastReceiver::class.java)
            intent.action = "com.example.myapplication.UPDATE_SETTINGS"
            sendBroadcast(intent)
            finish()
        }

        val number: EditText = findViewById(R.id.edit_text_phone_number)
        val message: EditText = findViewById(R.id.edit_text_messaje)
        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedNumber = sharedPref.getString("saved_number", "")
        val savedMessage = sharedPref.getString("saved_message", "")
        number.setText(savedNumber)
        message.setText(savedMessage)
    }
}
