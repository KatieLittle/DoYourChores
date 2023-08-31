package dev.smallcat.doyourchores

import android.app.Application

class DYCApp : Application() {
    companion object {
        lateinit var appModule: AppModule
        lateinit var instance : Application
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
        instance = this
    }
}