package com.pinterest.workmanageroom

import android.content.Context
import android.util.Log
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import io.reactivex.Single

class MyRxWorker(context: Context, workerParameters: WorkerParameters) :
    RxWorker(context, workerParameters) {

    override fun createWork(): Single<Result> = Single.create {
        val name = inputData.getString(MyWorker.WORKER_NAME)
        Log.i("TestWM", "Rx $name is Working")
        it.onSuccess(Result.success())
    }
}