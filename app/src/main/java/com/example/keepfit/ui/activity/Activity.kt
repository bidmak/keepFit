package com.example.keepfit.ui.activity

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.keepfit.KeepFitAppState
import com.example.keepfit.rememberKeepFitAppState
import com.example.keepfit.ui.*

@Composable
fun Activity(
    appState: KeepFitAppState = rememberKeepFitAppState(),
    bottomNavController: NavController
){
    NavHost(
        navController = appState.navController,
        startDestination = Screen.ActivityScreen.route
    ){
        composable(route = Screen.ActivityScreen.route){
            ActivityScreen(
                navController = appState.navController,
                bottomNavController = bottomNavController
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
                    defaultValue = "Goal"
                },
                navArgument(ACTIVITY_TARGET){
                    type = NavType.IntType
                    defaultValue = 5000
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
                bottomNavController = bottomNavController,
                onBackPress = appState::navigationBack,
                date = date!!,
                goalName = goalName!!,
                goalTarget = goalTarget!!,
                steps = steps!!
            )
        }
    }
}


