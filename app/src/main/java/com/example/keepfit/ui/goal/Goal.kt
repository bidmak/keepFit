package com.example.keepfit.ui.goal

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.keepfit.KeepFitAppState
import com.example.keepfit.rememberKeepFitAppState
import com.example.keepfit.ui.GOAL_NAME
import com.example.keepfit.ui.GOAL_TARGET
import com.example.keepfit.ui.Screen

@Composable
fun Goal(
    appState: KeepFitAppState = rememberKeepFitAppState(),
    bottomNavController: NavController
){
    NavHost(
        navController = appState.navController,
        startDestination = Screen.GoalScreen.route
    ){
        composable(route = Screen.GoalScreen.route){
            GoalScreen(navController = appState.navController,
                bottomNavController = bottomNavController
            )
        }
        composable(route = Screen.AddGoalScreen.route){
            AddGoalScreen(
                onBackPress = appState::navigationBack,
                bottomNavController = bottomNavController
            )
        }
        composable(
            route = Screen.EditGoalScreen.route,
            arguments = listOf(
                navArgument(GOAL_NAME){
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(GOAL_TARGET){
                    type = NavType.StringType
                    nullable = true
                }
            )
        ){
            val goalName = it.arguments?.getString(GOAL_NAME) ?: ""
            val goalTarget = it.arguments?.getString(GOAL_TARGET) ?: ""

            EditGoalScreen(
                bottomNavController = bottomNavController,
                onBackPress = appState::navigationBack,
                goalName = goalName,
                goalTarget = goalTarget
            )
        }
    }
}