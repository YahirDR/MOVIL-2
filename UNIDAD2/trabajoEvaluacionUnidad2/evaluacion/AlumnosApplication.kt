package com.example.autenticacionyconsulta

import android.app.Application
import com.example.autenticacionyconsulta.data.AppContainer
import com.example.autenticacionyconsulta.data.DefaultAppContainer

class AlumnosApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}