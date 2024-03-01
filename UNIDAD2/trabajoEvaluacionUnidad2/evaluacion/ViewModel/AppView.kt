package com.example.autenticacionyconsulta.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.autenticacionyconsulta.AlumnosApplication
import com.example.autenticacionyconsulta.data.AlumnosRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AppView(private val alumnosRepository: AlumnosRepository):ViewModel(){
    var matricula by mutableStateOf("")
    var password by mutableStateOf("")
    var errorLogin by mutableStateOf(false)
    var expandido by mutableStateOf(false)
    var tipoUsuario by mutableStateOf("ALUMNO")
    var kardex by mutableStateOf("")
    var horario by mutableStateOf("")

    fun updateExpandido(boolean: Boolean){
        expandido=boolean
    }
    fun updateTipoUsuario(string: String){
        tipoUsuario=string
    }

    fun updateMatricula(string: String){
        matricula=string
    }
    fun updatePassword(string: String){
        password=string
    }
    fun updateErrorLogin(boolean: Boolean){
        errorLogin=boolean
    }

    //-------------------ACCESO------------------------------
    suspend fun getAccess(matricula: String, password: String, tipoUsuario:String): Boolean {
        return alumnosRepository.getAccess(matricula, password, tipoUsuario)
    }

    //-------------------INFO PRINCIPAL------------------------------
    suspend fun getInfo():String{
        val informacion = viewModelScope.async {
            alumnosRepository.getInfo()
        }
        return informacion.await()
    }

    //-------------------KARDEX------------------------------
     fun getKardex(lineamiento:String?){
         viewModelScope.launch {
             val kard = viewModelScope.async {
                 alumnosRepository.getKardex(
                     lineamiento?.let {
                         it
                     } ?: "")
             }
             kardex= kard.await()
         }
    }

    //-------------------HORARIO------------------------------
    fun getHorario(){
        viewModelScope.launch {
           horario=alumnosRepository.getHorario()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AlumnosApplication)
                val alumnosAplication = application.container.alumnosRepository
                AppView(alumnosRepository = alumnosAplication)
            }
        }
    }
}

