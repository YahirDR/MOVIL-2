package com.example.autenticacionyconsulta.workers.DB

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.autenticacionyconsulta.AlumnosApplication
import com.example.autenticacionyconsulta.modelos.CredencialesAlumno
import com.example.autenticacionyconsulta.modelos.FinalesAlumno
import com.example.autenticacionyconsulta.modelos.HorarioAlumno
import com.example.autenticacionyconsulta.modelos.InformacionAlumno
import com.example.autenticacionyconsulta.modelos.ParcialesAlumno
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
// clase WorkManager que extiende CoroutineWorker, usar para tareas en segundo plano.
class WorkerDBFinales (ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {
    // Se inicializa la variable contexto con el repositorio de la base de datos de alumnos
    var contexto=(ctx.applicationContext as AlumnosApplication).container.alumnosRepositoryDB
    // Función suspendida que realiza el trabajo en segundo plano
    override suspend fun doWork(): Result {
        // Se obtiene el JSON de finales desde los datos de entrada
        var jsonFinales=inputData.getString("finales")

        if(!jsonFinales.isNullOrEmpty()){
            // Se define un tipo de token para convertir el JSON a una lista de objetos FinalesAlumno
            var type = object : TypeToken<List<FinalesAlumno>>() {}.type
            // Se convierte el JSON a una lista de objetos FinalesAlumno
            var finales: List<FinalesAlumno> = Gson().fromJson(jsonFinales, type)
            // Se elimina la información de finales existente en la base de datos
            contexto.deleteFinales()
            // Se inserta cada objeto FinalesAlumno en la base de datos
            finales.forEach {
                contexto.insertFinal(it)
            }
            return Result.success()
        }
        return  Result.failure()
    }
}