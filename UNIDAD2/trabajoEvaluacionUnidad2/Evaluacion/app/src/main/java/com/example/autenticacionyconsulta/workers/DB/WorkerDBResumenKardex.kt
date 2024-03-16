package com.example.autenticacionyconsulta.workers.DB

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.autenticacionyconsulta.AlumnosApplication
import com.example.autenticacionyconsulta.modelos.ResumenKardex
import com.google.gson.Gson

class WorkerDBResumenKardex (ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {

    var contexto=(ctx.applicationContext as AlumnosApplication).container.alumnosRepositoryDB

    override suspend fun doWork(): Result {
        var jsonKardexResumen=inputData.getString("kardexResumen")

        if(!jsonKardexResumen.isNullOrEmpty()){
            var resumenKardex = Gson().fromJson(jsonKardexResumen, ResumenKardex::class.java)
            contexto.deleteResumenKardex()
            contexto.insertResumenKardex(resumenKardex)
            return Result.success()
        }
        return  Result.failure()
    }
}