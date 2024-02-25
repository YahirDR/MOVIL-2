package com.example.evaluacionU1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.evaluacionU1.dataStudent
import com.example.evaluacionU1.loginApp

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppPantallas.Login.route,
    ){
        composable(route =AppPantallas.Login.route){
            loginApp(navController)
        }
        composable(route =AppPantallas.Info.route+"{text}",
            arguments = listOf(navArgument(name = "text"){
                type= NavType.StringType
            })){
            dataStudent(navController,it.arguments?.getString("text"))
        }

    }
}