package com.example.autenticacionyconsulta.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.work.WorkInfo
import com.example.autenticacionyconsulta.AlumnosApplication
import com.example.autenticacionyconsulta.data.AlumnosRepository
import com.example.autenticacionyconsulta.data.AlumnosRepositoryDB
import com.example.autenticacionyconsulta.data.WorkerRepository
import com.example.autenticacionyconsulta.modelos.CredencialesAlumno
import com.example.autenticacionyconsulta.modelos.FinalesAlumno
import com.example.autenticacionyconsulta.modelos.HorarioAlumno
import com.example.autenticacionyconsulta.modelos.InformacionAlumno
import com.example.autenticacionyconsulta.modelos.KardexMateria
import com.example.autenticacionyconsulta.modelos.ParcialesAlumno
import com.example.autenticacionyconsulta.modelos.ResumenKardex
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AppView(private val workerRepository: WorkerRepository, private val alumnosRepositoryDB: AlumnosRepositoryDB):ViewModel(){
    // Variables de MutableState
    var matricula by mutableStateOf("")
    var password by mutableStateOf("")
    var fechaDatos by mutableStateOf("")
    var errorLogin by mutableStateOf(false)
    var internetDisponible by mutableStateOf(false)// verifica si hay conexión a internet disponible    .
    var errorInternet by mutableStateOf(false)
    var expandido by mutableStateOf(false)
    var sinFinales by mutableStateOf(false)
    var sinKardex by mutableStateOf(false)
    var sinParciales by mutableStateOf(false)
    var sinHorario by mutableStateOf(false)
    var kardex1Finalizado by mutableStateOf(false)
    var kardex1Actualizado by mutableStateOf(false)
    var kardex2Finalizado by mutableStateOf(false)
    var kardex2Actualizado by mutableStateOf(false)
    var tipoUsuario by mutableStateOf("ALUMNO")
    // Listas para contener mis entidades
    var kardex: MutableList<KardexMateria> = mutableListOf()
    var horario: MutableList<HorarioAlumno> = mutableListOf()
    var parciales: MutableList<ParcialesAlumno> = mutableListOf()
    var finales: MutableList<FinalesAlumno> = mutableListOf()
    var lunes: MutableList<HorarioAlumno> = mutableListOf()
    var martes: MutableList<HorarioAlumno> = mutableListOf()
    var miercoles: MutableList<HorarioAlumno> = mutableListOf()
    var jueves: MutableList<HorarioAlumno> = mutableListOf()
    var viernes: MutableList<HorarioAlumno> = mutableListOf()
    // Variables de MutableState
    var resumenKardex by mutableStateOf(ResumenKardex())
    var modoOffline by mutableStateOf(false)
    var credencialAlumno:CredencialesAlumno= CredencialesAlumno()
    var infoAlumno:InformacionAlumno= InformacionAlumno()
    var daClicBoton by mutableStateOf(false)
    var daClicFinales by mutableStateOf(false)
    var daClicKardex by mutableStateOf(false)
    var daClicParciales by mutableStateOf(false)
    var daClicHorario by mutableStateOf(false)
    var acceso by mutableStateOf(false)
    // StateFlows para observar el estado de la operación de acceso a datos del worker
    val workerUiState: StateFlow<WorkerAccessState> = workerRepository.outputWorkInfo
        // Se mapea el flujo de información para transformar los datos de salida del worker en un estado de acceso.
        .map { info ->
            // Se extraen los datos
            val acceso = info.outputData.getString("credencial")
            val informacion=info.outputData.getString("informacion")
            when {
                info.state.isFinished  && !acceso.isNullOrEmpty() && !informacion.isNullOrEmpty()-> {
                    WorkerAccessState.Complete(acceso, informacion)
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000,0),
            initialValue = WorkerAccessState.Default
        )

    val workerUiStateFinales: StateFlow<WorkerAccessState> = workerRepository.outputWorkFinales
        .map { info ->
            val finales = info.outputData.getString("finales")
            when {
                info.state.isFinished  && !finales.isNullOrEmpty()-> {
                    WorkerAccessState.Complete(finales,"")
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkerAccessState.Default
        )

    val workerUiStateParciales: StateFlow<WorkerAccessState> = workerRepository.outputWorkParciales
        .map { info ->
            val parciales = info.outputData.getString("parciales")
            when {
                info.state.isFinished  && !parciales.isNullOrEmpty()-> {
                    WorkerAccessState.Complete(parciales,"")
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkerAccessState.Default
        )

    val workerUiStateHorario: StateFlow<WorkerAccessState> = workerRepository.outputWorkHorario
        .map { info ->
            val horario = info.outputData.getString("horario")
            when {
                info.state.isFinished  && !horario.isNullOrEmpty()-> {
                    WorkerAccessState.Complete(horario,"")
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkerAccessState.Default
        )

    val workerUiStateKardexResumen: StateFlow<WorkerAccessState> = workerRepository.outputWorkKardexResumen
        .map { info ->
            val kardexR = info.outputData.getString("kardexResumen")
            when {
                info.state.isFinished  && !kardexR.isNullOrEmpty()-> {
                    WorkerAccessState.Complete(kardexR,"")
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkerAccessState.Default
        )

    val workerUiStateKardex: StateFlow<WorkerAccessState> = workerRepository.outputWorkKardex
        .map { info ->
            val kardex1 = info.outputData.getString("kardex")
            when {
                info.state.isFinished  && !kardex1.isNullOrEmpty()-> {
                    kardex1Finalizado=true
                    WorkerAccessState.Complete(kardex1,"")
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkerAccessState.Default
        )

    val workerUiStateKardex2: StateFlow<WorkerAccessState> = workerRepository.outputWorkKardex2
        .map { info ->
            val kardex2 = info.outputData.getString("kardex2")
            when {
                info.state.isFinished && !kardex2.isNullOrEmpty()-> {
                    kardex2Finalizado=true
                    WorkerAccessState.Complete(kardex2,"")
                }
                info.state == WorkInfo.State.FAILED -> {
                    WorkerAccessState.Default
                }
                else ->WorkerAccessState.Loading
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkerAccessState.Default)
    //Actualizadores
    fun updateInternetDisponible(boolean: Boolean){
        internetDisponible=boolean
    }
    fun updateDaClicBoton(boolean: Boolean){
        daClicBoton=boolean
    }
    fun updateDaClicFinales(boolean: Boolean){
        daClicFinales=boolean
    }
    fun updateDaClicHorario(boolean: Boolean){
        daClicHorario=boolean
    }
    fun updateDaClicParciales(boolean: Boolean){
        daClicParciales=boolean
    }
    fun updateDaClicKardex(boolean: Boolean){
        daClicKardex=boolean
    }
    fun updateAcceso(boolean: Boolean){
        acceso=boolean
    }

    fun updateErrorInternet(boolean: Boolean){
        errorInternet=boolean
    }

    fun updateExpandido(boolean: Boolean){
        expandido=boolean
    }

    fun updateOffline(boolean: Boolean){
        modoOffline=boolean
    }

    fun updateSinFinales(boolean: Boolean){
        sinFinales=boolean
    }

    fun updateSinParciales(boolean: Boolean){
        sinParciales=boolean
    }

    fun updateSinHorario(boolean: Boolean){
        sinHorario=boolean
    }

    fun updateSinKardex(boolean: Boolean){
        sinKardex=boolean
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
    fun updateFecha(string: String){
        fechaDatos=string
    }
    fun updateErrorLogin(boolean: Boolean){
        errorLogin=boolean
    }


    fun proporcionarAcceso(jsonCredencial:String,jsonInfo:String):Boolean{
        var credencial=Gson().fromJson(jsonCredencial, CredencialesAlumno::class.java)
        var informacion=Gson().fromJson(jsonInfo, InformacionAlumno::class.java)
        Log.d("worker",jsonCredencial)
        Log.d("worker",jsonInfo)
        if(credencial.acceso=="true" ){
            credencialAlumno=credencial
            infoAlumno=informacion
            return true
        }
        return false
    }

    // actualiza la lista de finales con los datos obtenidos del servidor en formato JSON.
    fun actualizarFinales(json:String){
        val type = object : TypeToken<List<FinalesAlumno>>() {}.type
        finales= Gson().fromJson(json, type)
    }
    // actualiza la lista de horarios con los datos obtenidos del servidor en formato JSON.

    fun actualizarHorario(json:String){
        val type = object : TypeToken<List<HorarioAlumno>>() {}.type
        horario= Gson().fromJson(json, type)
        limpiarListasDias()
        // Se divide el horario en diferentes listas según el día de la semana.
        horario.forEach{
            if(it.Lunes.length>0)
               lunes.add(it)
            if(it.Martes.length>0)
               martes.add(it)
            if(it.Miercoles.length>0)
                miercoles.add(it)
            if(it.Jueves.length>0)
                jueves.add(it)
            if(it.Viernes.length>0)
                viernes.add(it)
        }
    }
    // Actualiza la lista de horarios con los datos obtenidos de la base de datos local.
    fun actualizarHorarioDB(horario:MutableList<HorarioAlumno>){
        limpiarListasDias()
        horario.forEach{
            if(it.Lunes.length>0)
                lunes.add(it)
            if(it.Martes.length>0)
                martes.add(it)
            if(it.Miercoles.length>0)
                miercoles.add(it)
            if(it.Jueves.length>0)
                jueves.add(it)
            if(it.Viernes.length>0)
                viernes.add(it)
        }
    }

    fun limpiarListasDias(){
        lunes.clear()
        martes.clear()
        miercoles.clear()
        jueves.clear()
        viernes.clear()
    }
    // actualiza la lista de parciales con los datos obtenidos del servidor en formato JSON.
    fun actualizarParciales(json:String){
        val type = object : TypeToken<List<ParcialesAlumno>>() {}.type
        var parcialesLista:MutableList<ParcialesAlumno> = Gson().fromJson(json, type)
        parcialesLista.forEach {
            if (it.C1 == null)
                it.C1 = ""
            if (it.C2 == null)
                it.C2 = ""
            if (it.C3 == null)
                it.C3 = ""
            if (it.C4 == null)
                it.C4 = ""
            if (it.C5 == null)
                it.C5 = ""
            if (it.C6 == null)
                it.C6 = ""
            if (it.C7 == null)
                it.C7 = ""
            if (it.C8 == null)
                it.C8 = ""
            if (it.C9 == null)
                it.C9 = ""
            if (it.C10 == null)
                it.C10 = ""
            if (it.C11 == null)
                it.C11 = ""
            if (it.C12 == null)
                it.C12 = ""
            if (it.C13 == null)
                it.C13 = ""
        }
        parciales=parcialesLista
    }
    //  actualizar el resumen del kardex con los datos obtenidos cambiar a JSON.
    fun actualizarResumenKardex(jsonKardexResumen:String){
        resumenKardex= Gson().fromJson(jsonKardexResumen, ResumenKardex::class.java)
    }
    // Esta función actualiza la lista de materias del kardex 1 con los datos obtenidos del servidor en formato JSON.
    fun actualizarKardex1(jsonKardex:String){
        var type = object : TypeToken<List<KardexMateria>>() {}.type
        var kardexL: MutableList<KardexMateria> = Gson().fromJson(jsonKardex, type)
        kardexL.forEach {
            kardex.add(it)
        }
        kardex1Actualizado=true
    }
    // Esta función actualiza la lista de materias del kardex 2 con los datos obtenidos del servidor en formato JSON.
    fun actualizarKardex2(jsonKardex:String){
        var type = object : TypeToken<List<KardexMateria>>() {}.type
        var kardexL: MutableList<KardexMateria> = Gson().fromJson(jsonKardex, type)
        kardexL.forEach {
            kardex.add(it)
        }
        kardex2Actualizado=true
    }

    fun limpiarKardex(){
        kardex.clear()
    }

    //getAcceder
    fun getAccess() {
        if(internetDisponible){
            //  acceder al repositorio de los workers
            workerRepository.getAccess(matricula, password, tipoUsuario)
        }
    }

    suspend fun getAccessDB():Boolean{
        // Obtener las credenciales del alumno de la base de datos
        var credencial=alumnosRepositoryDB.getAccessDB(matricula.uppercase())
        credencial?.let {
            if(tipoUsuario.equals("ALUMNO")){
                if (credencial.contrasenia==password && credencial.tipoUsuario==0){
                    // Obtiene la información del alumno de la base de datos
                    infoAlumno=alumnosRepositoryDB.getInfoDB()
                    errorLogin=false
                    daClicBoton=false
                    modoOffline=true
                    return true
                }
                errorLogin=true
                return false
            }
        }
        errorInternet=true
        return false
    }

    suspend fun getFinalesDB(){
        //Obtener finales
        var finalesDatos=alumnosRepositoryDB.getFinalesDB()
        if(!finalesDatos.isNullOrEmpty()) {
            sinFinales = false
            finales = finalesDatos
        }else{
            sinFinales=true
        }
    }

    suspend fun getParcialesDB(){
        //Obtener parciales
        var parcialesDatos=alumnosRepositoryDB.getParcialesDB()
        if(!parcialesDatos.isNullOrEmpty()) {
            sinParciales = false
            parciales = parcialesDatos
        }else{
            sinParciales=true
        }
    }

    suspend fun getHorarioDB(){
        //Obtener horarios
        var horarioDatos=alumnosRepositoryDB.getHorarioDB()
        if(!horarioDatos.isNullOrEmpty()) {
            sinHorario = false
            horario = horarioDatos
            actualizarHorarioDB(horario)
        }else{
            sinHorario=true
        }
    }

    suspend fun getKardexDB(){
        // Obtener el kárdex y el resumen del kárdex de la base de datos
        var kardexDatos=alumnosRepositoryDB.getKardexDB(infoAlumno.lineamiento.toString())
        var kardexResumen=alumnosRepositoryDB.getResumenKardexDB()
        //Comprobar
        kardexResumen?.let {
            sinKardex=false
            kardex= kardexDatos!!
            resumenKardex=kardexResumen
        }?:run{
            sinKardex=true
        }

    }

    //Getters kardes
     fun getKardex(){
         if(internetDisponible){
             workerRepository.getKardex(infoAlumno.lineamiento.toString())
             workerRepository.getKardex2(infoAlumno.lineamiento.toString())
             workerRepository.getResumenKardex(infoAlumno.lineamiento.toString())
         }
    }

    //Getters kardex
    fun getHorario(){
        if(internetDisponible){
            workerRepository.getHorario()
        }
    }

    //Getters parciales
    fun getParciales(){
        if(internetDisponible){
            workerRepository.getParciales()
        }
    }
    //Getters finales
    fun getFinales(){
        if(internetDisponible){
            workerRepository.getFinales(infoAlumno.modEducativo)
        }
    }
    //CONTRUCCION de instancias del ViewModel
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Obtiene los repositorios necesarios desde la aplicación

                val application = (this[APPLICATION_KEY] as AlumnosApplication)
                val workerRepository=application.container.workerRepository
                val alumnosRepositoryDB=application.container.alumnosRepositoryDB
                AppView( workerRepository = workerRepository,
                    alumnosRepositoryDB=alumnosRepositoryDB)
            }
        }
    }
}
// Interfaz sellada para representar los posibles estados de acceso al trabajador
sealed interface WorkerAccessState {
    object Default : WorkerAccessState
    object Loading : WorkerAccessState
    data class Complete(val outputUno: String,val outputDos: String) : WorkerAccessState
}

