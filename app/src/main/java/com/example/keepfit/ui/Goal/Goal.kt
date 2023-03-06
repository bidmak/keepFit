package com.example.keepfit.ui.Goal

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.keepfit.KeepFitAppState
import com.example.keepfit.rememberKeepFitAppState

@Composable
fun Goal(
    appState: KeepFitAppState = rememberKeepFitAppState()
){
    NavHost(
        navController = appState.navController,
        startDestination = "goalScreen"
    ){
        composable("goalScreen"){
            GoalScreen(navController = appState.navController)
        }
        composable("addGoalScreen"){
            AddGoalScreen(navController = appState.navController, onBackPress = appState::navigationBack)
        }
    }
}