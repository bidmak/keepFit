package com.example.keepfit.ui.Goal

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.keepfit.Graph
import com.example.keepfit.KeepFitAppState
import com.example.keepfit.data.entity.GoalData
import com.example.keepfit.data.repository.GoalDataRepository
import com.example.keepfit.rememberKeepFitAppState
import com.example.keepfit.ui.TopBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GoalScreen(
    navController: NavController
){
    val viewModel: GoalViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.padding(bottom = 60.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("addGoalScreen")},
                contentColor = Color.Black,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopBar(title = "Goals")
            GoalList(
                list = viewState.goals
            )
        }
    }
}


@Composable
private fun GoalList(
    list: List<GoalData>
) {
    LazyColumn(
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        items(list){ item ->
            GoalListItems(
                goal = item
            )
        }
    }
}


@Composable
private fun GoalListItems(
    goal: GoalData,
    viewModel: GoalViewModel = viewModel(),
    navController: NavController = rememberKeepFitAppState().navController
){
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F1F1)),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 3.dp)
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
                        .fillMaxWidth(0.7f),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = goal.goalName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "${goal.goalTarget} steps",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
                IconButton(onClick = {
                    navController.navigate("goalScreen")

                } ) {
                    Icon(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        imageVector = Icons.Default.Edit,
                        contentDescription = "edit",
                        tint = Color.Black)
                }
                IconButton(onClick = {
                    coroutineScope.launch {
                        viewModel.deleteGoal(goal)
                    }
                }) {
                    Icon(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete",
                        tint = Color.Black)
                }
            }
        }
    }

}

