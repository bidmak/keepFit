package com.example.keepfit.ui.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.keepfit.KeepFitAppState
import com.example.keepfit.rememberKeepFitAppState
import com.example.keepfit.ui.*
import com.example.keepfit.ui.theme.*

@Composable
fun History(
    appState: KeepFitAppState = rememberKeepFitAppState(),
    bottomNavController: NavController
){
    NavHost(
        navController = appState.navController,
        startDestination = Screen.HistoryRecordScreen.route
    ){
        composable(route = Screen.HistoryRecordScreen.route){
            HistoryRecordScreen(
                navController = appState.navController,
                bottomNavController = bottomNavController
            )
        }

        composable(route = Screen.HistoryScreen.route){
            HistoryScreen(
                navController = appState.navController,
                bottomNavController = bottomNavController
            )
        }

        composable(
            route = Screen.EditHistoryRecordScreen.route,
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

            EditHistoryRecordScreen(
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
