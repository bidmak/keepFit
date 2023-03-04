package com.example.keepfit.ui.Home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.keepfit.Activity
import com.example.keepfit.KeepFitAppState
import com.example.keepfit.rememberKeepFitAppState

@Composable
fun Home(
    appState: KeepFitAppState = rememberKeepFitAppState()
){
    NavHost(
        navController = appState.navController,
        startDestination = "homeScreen"
    ){
        composable("homeScreen"){
            HomeScreen(navController = appState.navController, activities = Activity())
        }
        composable("editGoalScreen"){
            EditGoalScreen(appState::navigationBack)
        }
    }
}