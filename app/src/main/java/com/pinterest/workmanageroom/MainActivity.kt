package com.pinterest.workmanageroom

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        val workManager = WorkManager.getInstance(this)
        for (i in 1..50) {
            Log.i("MyWorker", "Queueing $i worker")
            val worker = OneTimeWorkRequest.Builder(MyWorker::class.java)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()
            val uniqueName = "Worker-$i"
            workManager.enqueueUniqueWork(uniqueName, ExistingWorkPolicy.KEEP, worker)
        }
    }
}

class MyWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        Log.i("MyWorker", "Working")
        return Result.success()
    }
}