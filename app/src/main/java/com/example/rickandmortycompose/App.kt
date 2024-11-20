package com.example.rickandmortycompose

import android.app.Application
import com.example.rickandmortycompose.data.dataModule
import com.example.rickandmortycompose.ui.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(dataModule)
            modules(uiModule)
        }
    }
}