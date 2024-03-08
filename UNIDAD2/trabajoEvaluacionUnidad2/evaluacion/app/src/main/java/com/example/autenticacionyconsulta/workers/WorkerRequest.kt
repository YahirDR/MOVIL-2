package com.example.autenticacionyconsulta.workers

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkerRequest (ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params){
    @SuppressLint("RestrictedApi")
    override suspend fun doWork(): Result {

        return  withContext(Dispatchers.IO){
            return@withContext try {

                Result.Success()
            }catch (throwable: Throwable){
                Result.failure()
            }
        }
    }
}