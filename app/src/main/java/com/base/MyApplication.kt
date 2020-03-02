package com.base

import android.app.Application
import com.base.BuildConfig
import com.base.di.Modules
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        insertKoin()
        initStetho()
        plantTimber()
    }

    private fun plantTimber() {
        Timber.plant()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun insertKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    Modules.mainActivityModule,
                    Modules.decisionModule,
                    Modules.loginModule,
                    Modules.splashModule
                )
            )
        }
    }

}
