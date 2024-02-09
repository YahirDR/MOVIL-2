package com.example.bluromatic.workers

import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import android.content.Context
import com.example.bluromatic.R

class BlurWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
}

private const val TAG = "BlurWorker"
override suspend fun doWork(): Result {

    makeStatusNotification(
        applicationContext.resources.getString(R.string.blurring_image),
        applicationContext
    )
    makeStatusNotification(
        applicationContext.resources.getString(R.string.blurring_image),
        applicationContext
    )

    return try {
    } catch (throwable: Throwable) {
    }
    makeStatusNotification(
        applicationContext.resources.getString(R.string.blurring_image),
        applicationContext
    )

    return try {
        Result.success()
    } catch (throwable: Throwable) {
        Result.failure()
    }
    return try {
        val picture = BitmapFactory.decodeResource(
            applicationContext.resources,
            R.drawable.android_cupcake
        )

        Result.success()
        val picture = BitmapFactory.decodeResource(
            applicationContext.resources,
            R.drawable.android_cupcake
        )

        val output = blurBitmap(picture, 1)

        Result.success()
        val output = blurBitmap(picture, 1)

        // Write bitmap to a temp file
        val outputUri = writeBitmapToFile(applicationContext, output)

        Result.success()
        val output = blurBitmap(picture, 1)

        // Write bitmap to a temp file
        val outputUri = writeBitmapToFile(applicationContext, output)

        Result.success()