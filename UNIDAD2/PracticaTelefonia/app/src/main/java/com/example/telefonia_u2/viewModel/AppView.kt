package com.example.telefonia_u2.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

class AppView: ViewModel() {
    var numero by mutableStateOf("")
    var mensaje by mutableStateOf("")
    var MensajeEnviado by mutableStateOf(false)

    fun actualizarTelefono(dato: String){
        numero = dato
        MensajeEnviado = false
    }

    fun actualizarMensaje(dato: String){
        mensaje = dato
        MensajeEnviado = false
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                AppView()
            }
        }
    }

}