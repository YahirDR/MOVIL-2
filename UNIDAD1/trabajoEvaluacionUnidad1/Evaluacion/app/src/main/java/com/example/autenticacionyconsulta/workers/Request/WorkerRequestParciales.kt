package com.example.autenticacionyconsulta.workers.Request

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.autenticacionyconsulta.AlumnosApplication

class WorkerRequestParciales (ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params){

    var contexto=(ctx.applicationContext as AlumnosApplication).container.alumnosRepository

    override suspend fun doWork(): Result {
        try {
            var parciales=contexto.getParciales()
            var outputData = workDataOf("parciales" to parciales)
            return Result.success(outputData)
        }catch (e:Exception){
            return Result.failure()
        }

    }
}