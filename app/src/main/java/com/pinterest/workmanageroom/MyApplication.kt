package com.pinterest.workmanageroom

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import java.util.concurrent.Executors

class MyApplication : Application(), Configuration.Provider {
    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setExecutor(Executors.newFixedThreadPool(2))
            .setMinimumLoggingLevel(Log.VERBOSE)
            .build()
    }
}