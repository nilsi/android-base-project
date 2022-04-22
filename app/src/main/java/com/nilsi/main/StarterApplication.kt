package com.nilsi.main

import android.app.Application
import com.nilsi.main.core.networkModule
import com.nilsi.main.core.repoModule
import com.nilsi.main.core.storageModule
import com.nilsi.main.core.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class StarterApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@StarterApplication)
            modules(
                listOf(
                    viewModelModule,
                    repoModule,
                    storageModule,
                    networkModule
                )
            )
        }
    }
}