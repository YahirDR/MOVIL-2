package com.example.evaluacionU1.navigation

sealed class AppPantallas(val route:String) {
    object Login:AppPantallas("Login")
    object Info:AppPantallas("Info")
}