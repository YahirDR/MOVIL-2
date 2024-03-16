package com.example.autenticacionyconsulta.workers.Request

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.autenticacionyconsulta.AlumnosApplication
// clase WorkManager  para realizar tareas en segundo plano, en la obtencion de datos
class WorkerRequestFinales (ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params){
    // Se obtiene el contexto de la aplicación y accede al repositorio de los datos de los alumnos
    var contexto=(ctx.applicationContext as AlumnosApplication).container.alumnosRepository

    override suspend fun doWork(): Result {
        var modo=inputData.getString("modoEducativo")
        if(!modo.isNullOrEmpty()){
            var finales=contexto.getFinales(modo)
            var outputData = workDataOf("finales" to finales)
            return Result.success(outputData)
        }
        return Result.failure()
    }
}