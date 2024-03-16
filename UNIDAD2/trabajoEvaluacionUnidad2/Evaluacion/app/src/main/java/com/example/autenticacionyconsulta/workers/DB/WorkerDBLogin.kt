package com.example.autenticacionyconsulta.workers.DB

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.autenticacionyconsulta.AlumnosApplication
import com.example.autenticacionyconsulta.modelos.CredencialesAlumno
import com.example.autenticacionyconsulta.modelos.HorarioAlumno
import com.example.autenticacionyconsulta.modelos.InformacionAlumno
import com.example.autenticacionyconsulta.modelos.ParcialesAlumno
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
// Definición de la clase  que extiende CoroutineWorker,tareas en segundo plano.

class WorkerDBLogin(ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {

    var contexto=(ctx.applicationContext as AlumnosApplication).container.alumnosRepositoryDB

    override suspend fun doWork(): Result {
        var jsonAcceso=inputData.getString("credencial")
        var jsonInfo=inputData.getString("informacion")
        var password=inputData.getString("contra")

        if(!jsonAcceso.isNullOrEmpty() && !jsonInfo.isNullOrEmpty()){
                    var credencial= Gson().fromJson(jsonAcceso, CredencialesAlumno::class.java)
                    credencial.contrasenia=password.let { it }?:""
                    contexto.deleteCredenciales()
                    contexto.insertCredenciales(credencial)
                    var informacion= Gson().fromJson(jsonInfo, InformacionAlumno::class.java)
                    contexto.deleteInformacion()
                    contexto.insertInformacion(informacion)
                    return Result.success()
        }
        return  Result.failure()
    }
}