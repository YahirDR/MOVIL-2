package com.example.telefonia_u2.BroadcasReceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.PhoneStateListener
import android.telephony.ServiceState
import android.telephony.SignalStrength
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import com.example.telefonia_u2.SharedViewModel

class BroadcastReceiver: BroadcastReceiver() {
    // Variables de clase
    private var listener: ServiceStateListener? = null
    private var telephonyManager: TelephonyManager? = null
    private var context: Context? = null

    private var mensajeEnviado = false

    // Método onReceive, que se llama cuando se detecta un cambio en el estado del teléfono

    override fun onReceive(context2: Context, intent: Intent){
        // Se obtiene la acción que desencadenó la recepción
        val action = intent.action
        context = context2
        // Se verifica si la acción es un cambio en el estado del teléfono
        if(action == TelephonyManager.ACTION_PHONE_STATE_CHANGED){
            // Se obtiene el servicio de TelephonyManager
            telephonyManager = context2.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            // Se obtiene el número entrante
            var obtenerNumero = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
            var numeroReal = SharedViewModel.viewModel.numero

            // Verifica el estado
            if (telephonyManager?.callState == TelephonyManager.CALL_STATE_RINGING && !mensajeEnviado){
                if(obtenerNumero != null && obtenerNumero == numeroReal){
                    mensaje(obtenerNumero)
                    // Marca el mensaje como enviado
                    mensajeEnviado = true
                }
            } else if (telephonyManager?.callState == TelephonyManager.CALL_STATE_IDLE){
                // Restablece la variable cuando la llamada termina
                mensajeEnviado = false
            }

            Toast.makeText(context, "¡Llamada detectad!", Toast.LENGTH_LONG).show()
            listener = ServiceStateListener()
            telephonyManager?.listen(listener, PhoneStateListener.LISTEN_SERVICE_STATE)
            telephonyManager?.listen(listener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS)
        }
        Log.d("Llamada detectada","Se detecto")
    }
    // Método  para enviar un mensaje de texto
    private fun mensaje(
        numero: String
    ){
        val sms = SmsManager.getDefault()
        val mensaje = SharedViewModel.viewModel.mensaje
        sms.sendTextMessage(numero, null, mensaje, null, null)
        Log.d("Mensaje Enviado",sms.toString())
    }
    // Clase manejar los cambios en el estado del servicio y la intensidad de la señal
    private inner class ServiceStateListener: PhoneStateListener(){
        override fun onServiceStateChanged(serviceState: ServiceState) {
            super.onServiceStateChanged(serviceState)
            val conectado = serviceState.state == ServiceState.STATE_IN_SERVICE
            if (conectado) {
                Toast.makeText(context, "Se establecio la conexión ", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Se perdio la conexion perdida", Toast.LENGTH_LONG).show()
            }
        }

        override fun onSignalStrengthsChanged(signalStrength: SignalStrength) {
            super.onSignalStrengthsChanged(signalStrength)
            Toast.makeText(
                context,
                "Señal cambiada  ${signalStrength.cdmaDbm} GSM: ${signalStrength.gsmSignalStrength}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}