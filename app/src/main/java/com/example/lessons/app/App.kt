package com.example.lessons.app

import android.app.Application
import com.example.lessons.di.appModule
import com.example.lessons.di.dataModule
import com.example.lessons.di.domainModule
import com.example.lessons.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    companion object {
        lateinit var context: App
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        startKoin {
            modules(listOf(appModule, dataModule, domainModule, networkModule))
            androidContext(context)
        }
    }
}