package com.example.keepfit.ui.Activity

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.keepfit.data.entity.ActivityData
import com.example.keepfit.KeepFitAppState
import com.example.keepfit.rememberKeepFitAppState
import java.util.*

@Composable
fun Activity(
    appState: KeepFitAppState = rememberKeepFitAppState()
){
    NavHost(
        navController = appState.navController,
        startDestination = "homeScreen"
    ){
        composable("homeScreen"){
            ActivityScreen(
                navController = appState.navController,
            )
        }
        composable("editGoalScreen"){
            EditGoalScreen(navController = appState.navController)
        }
    }
}


