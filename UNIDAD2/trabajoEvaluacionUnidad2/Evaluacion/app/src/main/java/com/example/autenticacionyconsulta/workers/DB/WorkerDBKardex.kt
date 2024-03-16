package com.example.autenticacionyconsulta.workers.DB

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.autenticacionyconsulta.AlumnosApplication
import com.example.autenticacionyconsulta.modelos.CredencialesAlumno
import com.example.autenticacionyconsulta.modelos.HorarioAlumno
import com.example.autenticacionyconsulta.modelos.InformacionAlumno
import com.example.autenticacionyconsulta.modelos.KardexMateria
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
// Definición de la clase  que extiende CoroutineWorker,tareas en segundo plano.

class WorkerDBKardex (ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {
    // Se inicializa la variable contexto con el repositorio de la base de datos de alumnos
    var contexto=(ctx.applicationContext as AlumnosApplication).container.alumnosRepositoryDB
    // Función suspendida que realiza el trabajo en segundo plano
    override suspend fun doWork(): Result {
        var jsonKardex1=inputData.getString("kardex")

        if(!jsonKardex1.isNullOrEmpty()){
            // Se define un token para convertir el JSON a una lista de objetos KardexMateria
            var type = object : TypeToken<List<KardexMateria>>() {}.type
            // Se convierte el JSON a una lista de objetos KardexMateria
            var kardex: List<KardexMateria> = Gson().fromJson(jsonKardex1, type)
            contexto.deleteKardex()
            kardex.forEach {
                if(it.A1==null)
                    it.A1=""
                if(it.A2==null)
                    it.A2=""
                if(it.A3==null)
                    it.A3=""
                if(it.P1==null)
                    it.P1=""
                if(it.P2==null)
                    it.P2=""
                if(it.P3==null)
                    it.P3=""
                if(it.S1==null)
                    it.S1=""
                if(it.S2==null)
                    it.S2=""
                if(it.S3==null)
                    it.S3=""
                contexto.insertKardex(it)
            }
            return Result.success()
        }
        return  Result.failure()
    }
}