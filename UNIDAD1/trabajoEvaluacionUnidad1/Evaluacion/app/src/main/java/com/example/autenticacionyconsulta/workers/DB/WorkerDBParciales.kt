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

class WorkerDBParciales (ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {

    var contexto=(ctx.applicationContext as AlumnosApplication).container.alumnosRepositoryDB

    override suspend fun doWork(): Result {
        var jsonParciales=inputData.getString("parciales")

        if(!jsonParciales.isNullOrEmpty()){
            var type = object : TypeToken<List<ParcialesAlumno>>() {}.type
            var parciales: List<ParcialesAlumno> = Gson().fromJson(jsonParciales, type)
            contexto.deleteParciales()
            parciales.forEach {
                if(it.C1==null)
                    it.C1=""
                if(it.C2==null)
                    it.C2=""
                if(it.C3==null)
                    it.C3=""
                if(it.C4==null)
                    it.C4=""
                if(it.C5==null)
                    it.C5=""
                if(it.C6==null)
                    it.C6=""
                if(it.C7==null)
                    it.C7=""
                if(it.C8==null)
                    it.C8=""
                if(it.C9==null)
                    it.C9=""
                if(it.C10==null)
                    it.C10=""
                if(it.C11==null)
                    it.C11=""
                if(it.C12==null)
                    it.C12=""
                if(it.C13==null)
                    it.C13=""
                contexto.insertParciales(it)
            }
            return Result.success()
        }
        return  Result.failure()
    }
}