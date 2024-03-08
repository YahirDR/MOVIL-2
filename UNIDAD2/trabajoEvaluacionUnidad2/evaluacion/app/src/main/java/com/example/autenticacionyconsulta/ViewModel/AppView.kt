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
import com.example.autenticacionyconsulta.Horario
import com.example.autenticacionyconsulta.Parciales
import com.example.autenticacionyconsulta.data.AlumnosRepository
import com.example.autenticacionyconsulta.modelos.CredencialesAlumno
import com.example.autenticacionyconsulta.modelos.FinalesAlumno
import com.example.autenticacionyconsulta.modelos.HorarioAlumno
import com.example.autenticacionyconsulta.modelos.KardexMateria
import com.example.autenticacionyconsulta.modelos.ParcialesAlumno
import com.example.autenticacionyconsulta.modelos.ResumenKardex
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AppView(private val alumnosRepository: AlumnosRepository):ViewModel(){
    var matricula by mutableStateOf("")
    var password by mutableStateOf("")
    var errorLogin by mutableStateOf(false)
    var expandido by mutableStateOf(false)
    var tipoUsuario by mutableStateOf("ALUMNO")
    var kardex: MutableList<KardexMateria> = mutableListOf()
    var horario: MutableList<HorarioAlumno> = mutableListOf()
    var parciales by mutableStateOf(emptyList<ParcialesAlumno>())
    var finales by mutableStateOf(emptyList<FinalesAlumno>())
    var lunes: MutableList<HorarioAlumno> = mutableListOf()
    var martes: MutableList<HorarioAlumno> = mutableListOf()
    var miercoles: MutableList<HorarioAlumno> = mutableListOf()
    var jueves: MutableList<HorarioAlumno> = mutableListOf()
    var viernes: MutableList<HorarioAlumno> = mutableListOf()
    var noValido by mutableStateOf(true)
    var resumenKardex by mutableStateOf(ResumenKardex())

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

    //INICIAR SESION
    suspend fun getAccess(matricula: String, password: String, tipoUsuario:String): Boolean {
        var result= alumnosRepository.getAccess(matricula, password, tipoUsuario)
        if(result.isNotEmpty())
            return true
        else
            return false
    }

    //Get Alumno Info
    suspend fun getInfo():String{
        val informacion = viewModelScope.async {
            alumnosRepository.getInfo()
        }
        return informacion.await()
    }

    //-------------------KARDEX------------------------------
     suspend fun getKardex(lineamiento:String?){ val kard = alumnosRepository.getKardex(lineamiento?.let { it } ?: "0").split("[","]")
        var json="["+kard.get(1)+"]"
        val type = object : TypeToken<List<KardexMateria>>() {}.type
        kardex= Gson().fromJson(json, type)
        var json2="{"+kard.get(2).split("{","}").get(1)+"}"
        resumenKardex= Gson().fromJson(json2, ResumenKardex::class.java)
    }

    //Get Horario
    suspend fun getHorario(){
        var horary=alumnosRepository.getHorario().split("[\r\n  ","]")
        var json="["+horary.get(1)+"]"
        val type = object : TypeToken<List<HorarioAlumno>>() {}.type
        horario= Gson().fromJson(json, type)
    }

    //Get Calificaciones
    suspend fun getParciales(){
        var parcial=alumnosRepository.getParciales().split("[\r\n  ","]")
        var json="["+parcial.get(1)+"]"
        val type = object : TypeToken<List<ParcialesAlumno>>() {}.type
        parciales = Gson().fromJson(json, type)
    }
    //Get calificaciones Finales
    suspend fun getFinales(modEducativo:String?){
        var final=alumnosRepository.getFinales(modEducativo?.let { it }?:"0").split("[\r\n  ","]")
        var json="["+final.get(1)+"]"
        val type = object : TypeToken<List<FinalesAlumno>>() {}.type
        finales= Gson().fromJson(json, type)
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

