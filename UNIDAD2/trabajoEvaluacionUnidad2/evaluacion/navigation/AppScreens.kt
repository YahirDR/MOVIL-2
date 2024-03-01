package com.example.autenticacionyconsulta.navigation

sealed class AppScreens(val route:String) {
    object Login:AppScreens("Login")
    object Info:AppScreens("Info")
    object Kardex:AppScreens("Kardex")
    object Horario:AppScreens("Horario")
}