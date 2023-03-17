package com.example.keepfit.ui.goal

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
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.keepfit.PreferenceViewModel
import com.example.keepfit.data.entity.PreferenceData
import com.example.keepfit.ui.*
import com.example.keepfit.ui.activity.activeGoal
import com.example.keepfit.ui.theme.*
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GoalScreen(
    navController: NavController,
    bottomNavController: NavController
){
    val viewModel: GoalViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()

    val preferenceViewModel: PreferenceViewModel = viewModel()
    val preferenceViewState by preferenceViewModel.state.collectAsState()

    val goalPreference = preferenceViewState.preferences.firstOrNull{preference -> preference.id == 1
    } ?: PreferenceData(preference = false)

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {TopBar(
            title = "Goals", settings = true,
            show = goalPreference.preference,
            editGoal = true,
            showId = goalPreference.id
            )},
        bottomBar = { BottomNavigationBar(
            onItemClick = { bottomNavController.navigate(it.route) },
            navController = bottomNavController
        ) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(route = Screen.AddGoalScreen.route)},
                contentColor = Color.Black,
                modifier = Modifier.padding(20.dp),
                backgroundColor = ButtonColor
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {


            val list = viewState.goals
            LazyColumn(
                contentPadding = PaddingValues(0.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                items(list){ item ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        if(activeGoal == item.goalName ){
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 5.dp, vertical = 2.dp)
                                    .fillMaxWidth()
                                    .shadow(elevation = 1.dp, shape = RoundedCornerShape(10.dp))
                                    .background(Color(0xFFB8A8D6))
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
                                            text = item.goalName,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                        Text(
                                            text = "${item.goalTarget} steps",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }

                    }
                }
                items(list){ item ->
                    if(activeGoal != item.goalName ){
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 5.dp, vertical = 2.dp)
                                .fillMaxWidth()
                                .shadow(elevation = 1.dp, shape = RoundedCornerShape(10.dp))
                                .background(BackgroundColorMain)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(0.7f),
                                    horizontalAlignment = Alignment.Start,
                                ) {
                                    Text(
                                        text = item.goalName,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.DarkGray
                                    )
                                    Text(
                                        text = "${item.goalTarget} steps",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.DarkGray
                                    )
                                }
                                if (goalPreference.preference){
                                    IconButton(onClick = {

                                        navController.navigate(
                                            route = Screen.EditGoalScreen.passGoal(
                                                id = item.id,
                                                goalName = item.goalName,
                                                goalTarget = "${item.goalTarget}"
                                            )
                                        )

                                    } ) {
                                        Icon(
                                            modifier = Modifier.padding(horizontal = 10.dp),
                                            imageVector = Icons.Default.Edit,
                                            contentDescription = "edit",
                                            tint = EditColor)
                                    }
                                    IconButton(onClick = {
                                        coroutineScope.launch {
                                            viewModel.deleteGoal(item)
                                        }
                                    }) {
                                        Icon(
                                            modifier = Modifier.padding(horizontal = 10.dp),
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "delete",
                                            tint = DeleteColor)
                                    }
                                }

                            }
                        }

                    }
                }
            }
        }
    }
}


