package com.example.keepfit.ui.history

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
import com.example.keepfit.ui.Screen
import com.example.keepfit.ui.TopBar
import com.example.keepfit.ui.activity.ActivityViewModel
import com.example.keepfit.ui.activity.ProgressBar
import com.example.keepfit.ui.theme.BackgroundColorMain
import com.example.keepfit.ui.theme.DeleteColor
import com.example.keepfit.ui.theme.EditColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HistoryScreen(
    navController: NavController
){
    val viewModel: ActivityViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColorMain)
        ) {
            TopBar(title = "History", menu = true, onClick = {navController.navigate(Screen.HistoryRecordScreen.route)})

            LazyColumn(
                contentPadding = PaddingValues(0.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                items(viewState.activities){ activityData ->
                    ListItems(
                        activity = activityData,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
private fun ListItems(
    activity: ActivityData,
    viewModel: ActivityViewModel = viewModel(),
    navController: NavController
){
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
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
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "${activity.goalTarget}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "${activity.steps} steps",
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
                    color = DeleteColor
                )

                IconButton(onClick = {
                    coroutineScope.launch {
                        viewModel.deleteActivity(activity)
                    }
                }) {
                    Icon(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 30.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete",
                        tint = EditColor
                    )
                }
            }
        }
    }
}