package com.example.autenticacionyconsulta.workers.Request

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.autenticacionyconsulta.AlumnosApplication
class WorkerRequestResumenKardex (ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params){

    var contexto=(ctx.applicationContext as AlumnosApplication).container.alumnosRepository

    override suspend fun doWork(): Result {
        var lineamiento=inputData.getString("lineamiento")

        if(!lineamiento.isNullOrEmpty()){
            var kardex=contexto.getKardex(lineamiento).split("[","]")
            var kardexR="{"+kardex.get(2).split("{","}").get(1)+"}"
            var outputData = workDataOf("kardexResumen" to kardexR)
            return Result.success(outputData)
        }
        return Result.failure()
    }
}