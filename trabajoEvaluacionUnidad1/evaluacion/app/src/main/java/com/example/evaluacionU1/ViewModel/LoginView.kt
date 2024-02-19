package com.example.evaluacionU1.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.evaluacionU1.AlumnosApplication
import com.example.evaluacionU1.Datos.AlumnosRepository
import kotlinx.coroutines.async

class LoginView(private val alumnosRepository: AlumnosRepository):ViewModel(){
    var matricula by mutableStateOf("")
    var password by mutableStateOf("")
    var errorLogin by mutableStateOf(false)

    fun updateMatricula(string: String){
        matricula=string
    }
    fun updatePassword(string: String){
        password=string
    }
    fun updateErrorLogin(boolean: Boolean){
        errorLogin=boolean
    }

    suspend fun getAccess(matricula: String, password: String): Boolean {
        return alumnosRepository.getAccess(matricula, password)
    }

    suspend fun getInfo():String{
        val informacion = viewModelScope.async {
            alumnosRepository.getInfo()
        }
        return informacion.await()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AlumnosApplication)
                val alumnosAplication = application.container.alumnosRepository
                LoginView(alumnosRepository = alumnosAplication)
            }
        }
    }
}

