package com.example.autenticacionyconsulta.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.autenticacionyconsulta.ui.sicenet.Finales
import com.example.autenticacionyconsulta.ui.sicenet.Horario
import com.example.autenticacionyconsulta.ui.sicenet.Kardex
import com.example.autenticacionyconsulta.ui.sicenet.Parciales
import com.example.autenticacionyconsulta.ViewModel.AppView
import com.example.autenticacionyconsulta.ui.sicenet.loginApp
import com.example.autenticacionyconsulta.ui.sicenet.principalHome

@Composable
fun AppNavigation(viewmodel:AppView){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.Login.route,
    ){
        composable(route =AppScreens.Login.route){
            loginApp(navController,viewmodel)
        }
        composable(route =AppScreens.Info.route){
            principalHome(navController,viewmodel)
        }
        composable(route =AppScreens.Kardex.route){
            Kardex(navController, viewmodel)
        }
        composable(route =AppScreens.Horario.route){
            Horario(navController, viewmodel)
        }
        composable(route =AppScreens.Finales.route){
            Finales(navController, viewmodel)
        }
        composable(route =AppScreens.Parciales.route){
            Parciales(navController, viewmodel)
        }
    }
}