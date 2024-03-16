package com.example.autenticacionyconsulta.workers.Request

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.autenticacionyconsulta.AlumnosApplication

class WorkerRequestKardex2 (ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params){

    var contexto=(ctx.applicationContext as AlumnosApplication).container.alumnosRepository

    override suspend fun doWork(): Result {
        var lineamiento=inputData.getString("lineamiento")

        if(!lineamiento.isNullOrEmpty()){
            var kardex=contexto.getKardex(lineamiento).split("[","]")
            var kardexM=kardex.get(1).split("},{")
            var jsonKardex2="["
            for (i in kardexM.size/2..kardexM.size-1){
                if(i==(kardexM.size-1)){
                    jsonKardex2+="{"+kardexM.get(i)+"]"
                }else{
                    jsonKardex2+="{"+kardexM.get(i)+"},"
                }
            }
            var outputData = workDataOf("kardex2" to jsonKardex2)
            return Result.success(outputData)
        }
        return Result.failure()
    }
}