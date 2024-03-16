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

class WorkerDBHorario (ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {
    // Se inicializa la variable contexto con el repositorio de la base de datos de alumnos
    var contexto=(ctx.applicationContext as AlumnosApplication).container.alumnosRepositoryDB
    // Función suspendida que realiza el trabajo en segundo plano
    override suspend fun doWork(): Result {
        // Se obtiene el JSON de horario desde los datos de entrada
        var jsonHorario=inputData.getString("horario")

        if(!jsonHorario.isNullOrEmpty()){
            // Se define un oken para convertir el JSON a una lista de objetos HorarioAlumno
            var type = object : TypeToken<List<HorarioAlumno>>() {}.type
            // Se convierte el JSON a una lista de objetos
            var horario: List<HorarioAlumno> = Gson().fromJson(jsonHorario, type)
            contexto.deleteHorario()
            horario.forEach {
                contexto.insertHorario(it)
            }
            return Result.success()
        }
        return  Result.failure()
    }
}