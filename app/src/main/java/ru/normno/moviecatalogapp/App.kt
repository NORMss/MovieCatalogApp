package ru.normno.moviecatalogapp

import android.app.Application
import ru.normno.moviecatalogapp.di.AppModule.initializeKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }
}