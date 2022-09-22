package com.pinterest.workmanageroom

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.pinterest.workmanageroom.MyWorker.Companion.WORKER_NAME

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.queue_workers)
        button.setOnClickListener { queueWorkers() }
    }

    private fun queueRxWorkers() {
        val workManager = WorkManager.getInstance(this)
        for (i in 1..1_000) {
            Log.i("TestWM", "Queueing $i worker")
            val constraint = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            val uniqueName = "Worker-$i"

            val worker = OneTimeWorkRequestBuilder<MyRxWorker>()
                .setInputData(workDataOf(WORKER_NAME to "$uniqueName - work chain 1"))
                .setConstraints(constraint)
                .build()

            val worker2 = OneTimeWorkRequestBuilder<MyRxWorker>()
                .setInputData(workDataOf(WORKER_NAME to "$uniqueName - work chain 2"))
                .setConstraints(constraint)
                .build()

            val worker3 = OneTimeWorkRequestBuilder<MyRxWorker>()
                .setInputData(workDataOf(WORKER_NAME to "$uniqueName - work chain 3"))
                .setConstraints(constraint)
                .build()

            workManager.beginUniqueWork(uniqueName, ExistingWorkPolicy.KEEP, worker)
                .then(worker2)
                .then(worker3)
                .enqueue()
        }
    }

    private fun queueWorkers() {
        val workManager = WorkManager.getInstance(this)
        for (i in 1..1_000) {
            Log.i("TestWM", "Queueing $i worker")
            val constraint = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            val uniqueName = "Worker-$i"

            val worker = OneTimeWorkRequestBuilder<MyWorker>()
                .setInputData(workDataOf(WORKER_NAME to "$uniqueName - part 1"))
                .setConstraints(constraint)
                .build()

            val worker2 = OneTimeWorkRequestBuilder<MyWorker>()
                .setInputData(workDataOf(WORKER_NAME to "$uniqueName - part 2"))
                .setConstraints(constraint)
                .build()

            val worker3 = OneTimeWorkRequestBuilder<MyWorker>()
                .setInputData(workDataOf(WORKER_NAME to "$uniqueName - part 3"))
                .setConstraints(constraint)
                .build()

            workManager.beginUniqueWork(uniqueName, ExistingWorkPolicy.KEEP, worker)
                .then(worker2)
                .then(worker3)
                .enqueue()
        }
    }
}