package com.pinterest.workmanageroom

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    companion object {
        const val WORKER_NAME = "worker_name"
    }
    override fun doWork(): Result {
        val name = inputData.getString(WORKER_NAME)
        Log.i("TestWM", "$name is Working")
        return Result.success()
    }
}