package dev.smallcat.doyourchores

import android.app.Application
import dev.smallcat.doyourchores.di.ChoreModule
import dev.smallcat.doyourchores.di.ChoreModuleImpl

class DYCApp : Application() {
    companion object {
        lateinit var choreModule: ChoreModule
        lateinit var instance : Application
    }

    override fun onCreate() {
        super.onCreate()
        choreModule = ChoreModuleImpl(this)
        instance = this
    }
}