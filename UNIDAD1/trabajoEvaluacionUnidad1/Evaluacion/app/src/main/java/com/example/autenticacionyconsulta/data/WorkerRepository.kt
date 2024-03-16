package com.example.autenticacionyconsulta.data

import android.content.Context
import androidx.lifecycle.asFlow
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.autenticacionyconsulta.workers.DB.WorkerDBFinales
import com.example.autenticacionyconsulta.workers.DB.WorkerDBHorario
import com.example.autenticacionyconsulta.workers.DB.WorkerDBKardex
import com.example.autenticacionyconsulta.workers.DB.WorkerDBKardex2
import com.example.autenticacionyconsulta.workers.DB.WorkerDBResumenKardex
import com.example.autenticacionyconsulta.workers.DB.WorkerDBLogin
import com.example.autenticacionyconsulta.workers.DB.WorkerDBParciales
import com.example.autenticacionyconsulta.workers.Request.WorkerRequestFinales
import com.example.autenticacionyconsulta.workers.Request.WorkerRequestHorario
import com.example.autenticacionyconsulta.workers.Request.WorkerRequestKardex
import com.example.autenticacionyconsulta.workers.Request.WorkerRequestKardex2
import com.example.autenticacionyconsulta.workers.Request.WorkerRequestLogin
import com.example.autenticacionyconsulta.workers.Request.WorkerRequestParciales
import com.example.autenticacionyconsulta.workers.Request.WorkerRequestResumenKardex
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
//INTERFAZ PARA WORKERS
interface WorkerRepository {
    // Flujo de información sobre el trabajo con el acceso.
    val outputWorkInfo: Flow<WorkInfo>
    // Flujo de información sobre el trabajo con los finales.
    val outputWorkFinales: Flow<WorkInfo>
    // Flujo de información sobre el trabajocon los parciales.
    val outputWorkParciales: Flow<WorkInfo>
    // Flujo de información sobre el trabajo on el horario.
    val outputWorkHorario: Flow<WorkInfo>
    // Flujo de información sobre el trabajo con el kardex.
    val outputWorkKardex: Flow<WorkInfo>
    val outputWorkKardex2: Flow<WorkInfo>
    // Flujo de información sobre el trabajo  con el resumen del kardex.
    val outputWorkKardexResumen: Flow<WorkInfo>
    // Getters
    fun getAccess(matricula: String, password: String, tipoUsuario: String)
    fun getFinales(modoEducativo:String)
    fun getParciales()
    fun getHorario()
    fun getKardex(lineamiento:String)
    fun getKardex2(lineamiento:String)
    fun getResumenKardex(lineamiento:String)
}
// Repositorio   WorkManager para administrar las solicitudes de trabajo.
class WorkerManagement(context: Context): WorkerRepository {
    private val workManager = WorkManager.getInstance(context)
    // Métodos para iniciar  access.
    override fun getAccess(matricula: String, password: String, tipoUsuario: String) {
        // Define los datos de entrada para WorkerRequestLogin
        var data = workDataOf(
            "matricula" to matricula,
            "password" to password,
            "tipo" to tipoUsuario
        )

        // Crea una solicitud de trabajo única para WorkerRequestLogin
        var workerLogin = OneTimeWorkRequestBuilder<WorkerRequestLogin>()
            .setInputData(data)
            .addTag("WorkerAccess")
            .build()

        // Crea una solicitud de trabajo para WorkerDB
        var workerDbLogin = OneTimeWorkRequestBuilder<WorkerDBLogin>()
            .addTag("WorkerAccessInfo")
            .build()

        // Comienza el trabajo único y encadena el siguiente trabajo
        workManager.beginUniqueWork(
            "acceso",
            ExistingWorkPolicy.APPEND,
            workerLogin
        ).then(workerDbLogin) // Encadena workerDbLogin después de workerLogin
            .enqueue()
    }

    override fun getFinales(modoEducativo:String) {
        // Define los datos de entrada
        var data = workDataOf(
            "modoEducativo" to modoEducativo
        )

        // Crea una solicitud de trabajo única
        var workerFinales = OneTimeWorkRequestBuilder<WorkerRequestFinales>()
            .setInputData(data)
            .addTag("WorkerFinales")
            .build()

        //  Solicitud de trabajo para WorkerDBfinales
        var workerDbFinales = OneTimeWorkRequestBuilder<WorkerDBFinales>()
            .addTag("WorkerFinalesInfo")
            .build()

        // Comienza el trabajo único y encadena el siguiente trabajo
        workManager.beginUniqueWork(
            "finalesWorker",
            ExistingWorkPolicy.REPLACE,
            workerFinales
        ).then(workerDbFinales) // Encadena worker
            .enqueue()
    }

    override fun getParciales() {
        // Crea una solicitud de trabajo única
        var workerParciales = OneTimeWorkRequestBuilder<WorkerRequestParciales>()
            .addTag("WorkerParciales")
            .build()

        // Crea una solicitud de trabajo para WorkerDB
        var workerDbParciales= OneTimeWorkRequestBuilder<WorkerDBParciales>()
            .addTag("WorkerParcialesInfo")
            .build()

        // Comienza el trabajo único y encadena el siguiente trabajo
        workManager.beginUniqueWork(
            "parcialesWorker",
            ExistingWorkPolicy.REPLACE,
            workerParciales
        ).then(workerDbParciales) // Encadena worker
            .enqueue()
    }

    override fun getHorario() {
        // Crea una solicitud de trabajo única para WorkerRequestLogin
        var workerHorario = OneTimeWorkRequestBuilder<WorkerRequestHorario>()
            .addTag("WorkerHorario")
            .build()

        // Crea una solicitud de trabajo para WorkerDB
        var workerDbHorario = OneTimeWorkRequestBuilder<WorkerDBHorario>()
            .addTag("WorkerHorarioInfo")
            .build()

        // Comienza el trabajo único y encadena el siguiente trabajo
        workManager.beginUniqueWork(
            "horarioWorker",
            ExistingWorkPolicy.REPLACE,
            workerHorario
        ).then(workerDbHorario) // Encadena worker
            .enqueue()
    }

    override fun getKardex(lineamiento: String) {
        // Define los datos de entrada
        var data = workDataOf(
            "lineamiento" to lineamiento,
        )

        // Crea una solicitud de trabajo única para WorkerRequestLogin
        var workerKardex = OneTimeWorkRequestBuilder<WorkerRequestKardex>()
            .setInputData(data)
            .addTag("WorkerKardex")
            .build()

        // Crea una solicitud de trabajo para WorkerDB
        var workerDbKardex = OneTimeWorkRequestBuilder<WorkerDBKardex>()
            .addTag("WorkerKardexInfo")
            .build()

        // Comienza el trabajo único y encadena el siguiente trabajo
        workManager.beginUniqueWork(
            "kardexWorker",
            ExistingWorkPolicy.REPLACE,
            workerKardex
        ).then(workerDbKardex)// Encadena workerDb
            .enqueue()
    }

    override fun getKardex2(lineamiento: String) {
        // Define los datos de entrada
        var data = workDataOf(
            "lineamiento" to lineamiento,
        )

        // Crea una solicitud de trabajo única para WorkerRequestLogin
        var workerKardex2 = OneTimeWorkRequestBuilder<WorkerRequestKardex2>()
            .setInputData(data)
            .addTag("WorkerKardex2")
            .build()

        var workerDbKardex2 = OneTimeWorkRequestBuilder<WorkerDBKardex2>()
            .addTag("WorkerKardex2Info")
            .build()

        // Comienza el trabajo único y encadena el siguiente trabajo
        workManager.beginUniqueWork(
            "kardexWorker2",
            ExistingWorkPolicy.REPLACE,
            workerKardex2
        ).then(workerDbKardex2) // Encadena workerDb
            .enqueue()
    }

    override fun getResumenKardex(lineamiento: String) {
        var data = workDataOf(
            "lineamiento" to lineamiento,
        )

        var workerRK = OneTimeWorkRequestBuilder<WorkerRequestResumenKardex>()
            .setInputData(data)
            .addTag("WorkerResumenKardex")
            .build()

        var workerDbRK = OneTimeWorkRequestBuilder<WorkerDBResumenKardex>()
            .addTag("WorkerResumenKardexInfo")
            .build()

        // Comienza el trabajo único y encadena el siguiente trabajo
        workManager.beginUniqueWork(
            "kardexResumenWorker",
            ExistingWorkPolicy.REPLACE,
            workerRK
        ).then(workerDbRK) // Encadena workerDb
            .enqueue()

    }
// flujos de datos  sobre el estado de  tareas por WorkManager.
    override val outputWorkInfo: Flow<WorkInfo> =
    // Si la lista de WorkInfo no está vacía, devuelve el primer elemento de la lista
        workManager.getWorkInfosByTagLiveData("WorkerAccess").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }

    override val outputWorkFinales: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData("WorkerFinales").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }

    override val outputWorkParciales: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData("WorkerParciales").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }
    override val outputWorkHorario: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData("WorkerHorario").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }
    override val outputWorkKardex: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData("WorkerKardex").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }

    override val outputWorkKardex2: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData("WorkerKardex2").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }

    override val outputWorkKardexResumen: Flow<WorkInfo> =
        workManager.getWorkInfosByTagLiveData("WorkerResumenKardex").asFlow().mapNotNull {
            if (it.isNotEmpty()) it.first() else null
        }
}