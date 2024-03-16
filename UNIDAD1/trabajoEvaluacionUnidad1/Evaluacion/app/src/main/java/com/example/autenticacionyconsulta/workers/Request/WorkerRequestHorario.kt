package com.example.autenticacionyconsulta.workers.Request

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.autenticacionyconsulta.AlumnosApplication

class WorkerRequestHorario (ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params){

    var contexto=(ctx.applicationContext as AlumnosApplication).container.alumnosRepository

    override suspend fun doWork(): Result {
        try {
            var horario=contexto.getHorario()
            var outputData = workDataOf("horario" to horario)
            return Result.success(outputData)
        }catch (e:Exception){
            return Result.failure()
        }

    }
}