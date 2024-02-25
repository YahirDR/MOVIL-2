package com.example.evaluacionU1

import android.app.Application
import com.example.evaluacionU1.datos.AppContainer
import com.example.evaluacionU1.datos.DefaultAppContainer

class AlumnosApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}