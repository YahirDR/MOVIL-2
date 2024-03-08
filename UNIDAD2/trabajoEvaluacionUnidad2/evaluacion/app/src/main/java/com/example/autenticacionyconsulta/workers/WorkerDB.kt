package com.example.autenticacionyconsulta.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class WorkerDB(ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }
}