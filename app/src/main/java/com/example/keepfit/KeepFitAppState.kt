package com.example.keepfit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class KeepFitAppState(
    val navController: NavHostController
){
    fun navigationBack(){
        navController.popBackStack()
    }
}

@Composable
fun rememberKeepFitAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController){
    KeepFitAppState(navController)
}