package com.example.keepfit.ui.Activity

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.keepfit.KeepFitAppState
import com.example.keepfit.rememberKeepFitAppState
import com.example.keepfit.ui.*

@Composable
fun Activity(
    appState: KeepFitAppState = rememberKeepFitAppState()
){
    NavHost(
        navController = appState.navController,
        startDestination = Screen.ActivityScreen.route
    ){
        composable(route = Screen.ActivityScreen.route){
            ActivityScreen(
                navController = appState.navController,
            )
        }
        composable(
            route = Screen.EditActivityScreen.route,
            arguments = listOf(
                navArgument(ACTIVITY_DATE){
                    type = NavType.StringType
                    defaultValue = "Date"
                },
                navArgument(ACTIVITY_NAME){
                    type = NavType.StringType
                    defaultValue = "Goal Name"
                },
                navArgument(ACTIVITY_TARGET){
                    type = NavType.IntType
                    defaultValue = 6000
                },
                navArgument(ACTIVITY_STEP){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){
            val date = it.arguments?.getString(ACTIVITY_DATE)
            val goalName = it.arguments?.getString(ACTIVITY_NAME)
            val goalTarget = it.arguments?.getInt(ACTIVITY_TARGET)
            val steps = it.arguments?.getInt(ACTIVITY_STEP)
            EditActivityScreen(
                onBackPress = appState::navigationBack,
                date = date!!,
                goalName = goalName!!,
                goalTarget = goalTarget!!,
                steps = steps!!
            )
        }
    }
}


