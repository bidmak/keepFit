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
    appState: KeepFitAppState = rememberKeepFitAppState()
){
    NavHost(
        navController = appState.navController,
        startDestination = Screen.HistoryRecordScreen.route
    ){
        composable(route = Screen.HistoryRecordScreen.route){
            HistoryRecordScreen(
                navController = appState.navController
            )
        }

        composable(route = Screen.HistoryScreen.route){
            HistoryScreen(
                navController = appState.navController
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
                onBackPress = appState::navigationBack,
                date = date!!,
                goalName = goalName!!,
                goalTarget = goalTarget!!,
                steps = steps!!
            )
        }
    }
}






@Composable
fun HistoryScrn(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center

            ) {
                Text("Sunday, Feb 26",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.example.keepfit.R.drawable.baseline_calendar_month_24),
                    modifier = Modifier.padding(start = 5.dp),
                    contentDescription = "changeGoal",
                    tint = Color(0xFF7E57C2)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                Text("Mon", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Tue", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Wed", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Thu", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Fri", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Sat", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Sun", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Red)
            }
        }

        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                //ProgressBar(goalSteps = Activity().steps, goalTarget = 6000)
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 20.dp, bottom = 5.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text("Record",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .offset(12.dp)
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(20.dp))
                .background(Color.White)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 40.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Column(
                    modifier = Modifier
                        .padding(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Goal",
                        fontSize = 18.sp,
                        color = Color(0xFF689F38),
                        fontWeight = FontWeight.Bold
                    )
                    Text("Ambitious",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Target",
                        fontSize = 18.sp,
                        color = ButtonColor,
                        fontWeight = FontWeight.Bold
                    )
                    Text("6000",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text("steps",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = LightGrayColor
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Move",
                        fontSize = 18.sp,
                        color = MoveColor,
                        fontWeight = FontWeight.Bold
                    )

                    Text("5700",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text("steps",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = LightGrayColor
                    )
                }
            }

        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 28.dp, 0.dp, 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Button(
                modifier = Modifier.fillMaxWidth(0.8f),
                onClick = { /*TODO*/ },
                shape = CircleShape,
                contentPadding = PaddingValues(14.dp),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text("Clear History",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}
