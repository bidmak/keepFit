package com.example.keepfit.ui.history

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.keepfit.data.entity.ActivityData
import com.example.keepfit.ui.BottomNavigationBar
import com.example.keepfit.ui.Screen
import com.example.keepfit.ui.TopBar
import com.example.keepfit.ui.activity.ActivityViewModel
import com.example.keepfit.ui.activity.ProgressBar
import com.example.keepfit.ui.activity.toDayNumber
import com.example.keepfit.ui.theme.*
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HistoryScreen(
    navController: NavController,
    bottomNavController: NavController
){
    val viewModel: ActivityViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    val sortedActivities = viewState.activities.sortedByDescending{ toDayNumber(it.date) }

    Scaffold(
        topBar = { TopBar(title = "History", settings = true, onClick = {navController.navigate(Screen.HistoryRecordScreen.route)}) },
        bottomBar = { BottomNavigationBar(
            onItemClick = { bottomNavController.navigate(it.route) },
            navController = bottomNavController
        ) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        viewModel.deleteAllActivity(sortedActivities)
                    }
                },
                contentColor = Color.Black,
                modifier = Modifier.padding(20.dp),
                backgroundColor = BackgroundColor
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = DeleteColor
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColorMain)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(0.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                items(sortedActivities){ activityData ->
                    ListItems(
                        activity = activityData
                    )
                }
            }
        }
    }
}

@Composable
private fun ListItems(
    activity: ActivityData
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 2.dp)
                .fillMaxWidth()
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.3f),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = activity.date,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = activity.goalName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                    Text(
                        text = "${activity.goalTarget}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                    Text(
                        text = "Steps completed: ${activity.steps} steps",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }

                ProgressBar(
                    curActivity = activity,
                    radius = 54.dp,
                    strokeWidth = 5.dp,
                    fontSize = 15.sp,
                    showSteps = false,
                    color = AddButtonColor
                )
            }
        }
    }
}