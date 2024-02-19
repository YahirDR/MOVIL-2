package com.example.evaluacionU1

import android.app.Application
import com.example.evaluacionU1.Datos.AppContainer
import com.example.evaluacionU1.Datos.DefaultAppContainer

class AlumnosApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}