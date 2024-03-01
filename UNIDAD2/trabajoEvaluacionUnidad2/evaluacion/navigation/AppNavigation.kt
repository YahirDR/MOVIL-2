package com.example.autenticacionyconsulta.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.autenticacionyconsulta.Horario
import com.example.autenticacionyconsulta.Kardex
import com.example.autenticacionyconsulta.dataStudent
import com.example.autenticacionyconsulta.loginApp
import com.example.autenticacionyconsulta.modelos.InformacionAlumno
import com.example.autenticacionyconsulta.principalHome

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.Login.route,
    ){
        composable(route =AppScreens.Login.route){
            loginApp(navController)
        }
        composable(route =AppScreens.Info.route+"{text}",
            arguments = listOf(navArgument(name = "text"){
                type= NavType.StringType
            })){
            principalHome(navController,it.arguments?.getString("text"))
        }
        composable(route =AppScreens.Kardex.route+"{lineamiento}",
            arguments = listOf(navArgument(name = "lineamiento"){
                type= NavType.StringType
            })){
            Kardex(navController, it.arguments?.getString("lineamiento"))
        }
        composable(route =AppScreens.Horario.route){
            Horario(navController)
        }
    }
}