package com.example.keepfit.ui.history

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.keepfit.data.entity.ActivityData
import com.example.keepfit.ui.BottomNavigationBar
import com.example.keepfit.ui.EditTopBar
import com.example.keepfit.ui.activity.ActivityViewModel
import com.example.keepfit.ui.goal.GoalViewModel
import com.example.keepfit.ui.theme.ButtonColor
import com.example.keepfit.ui.theme.LightTextColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditHistoryRecordScreen(
    bottomNavController: NavController,
    onBackPress: () -> Unit,
    date: String,
    goalName: String,
    goalTarget: Int,
    steps: Int
){
    val activityViewModel: ActivityViewModel = viewModel()
    val curHistory = ActivityData(
        date = date,
        goalName = goalName,
        goalTarget = goalTarget,
        steps = steps
    )


    val goalViewModel: GoalViewModel = viewModel()
    val goalViewState by goalViewModel.state.collectAsState()

    val selectedGoalName = rememberSaveable { mutableStateOf(curHistory.goalName) }
    val selectedGoalTarget = rememberSaveable { mutableStateOf(curHistory.goalTarget) }
    val selectedSteps = rememberSaveable { mutableStateOf("${ curHistory.steps }") }

    var expanded by remember { mutableStateOf(false) }

    val icon = if(expanded){
        Icons.Filled.ArrowDropUp
    } else {
        Icons.Filled.ArrowDropDown
    }

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = { EditTopBar(title = "Edit History", back = true, onBackPress = onBackPress) },
        bottomBar = { BottomNavigationBar(
            onItemClick = { bottomNavController.navigate(it.route) },
            navController = bottomNavController
        ) },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 40.dp),
                horizontalArrangement = Arrangement.Center

            ) {
                Text("${curHistory.date}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = selectedGoalName.value,
                    onValueChange = {selectedGoalName.value = it},
                    modifier = Modifier.fillMaxWidth(0.9f),
                    label = { Text(text = "Goal") },
                    readOnly = true,
                    shape = CircleShape,
                    trailingIcon = {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.clickable { expanded = !expanded }
                        )
                    }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                )
                {
                    goalViewState.goals.forEach { curGoal ->
                        DropdownMenuItem(
                            onClick = {
                                selectedGoalName.value = curGoal.goalName
                                selectedGoalTarget.value = curGoal.goalTarget
                                expanded = false
                            }) {
                            Text(text = "${curGoal.goalName} (${curGoal.goalTarget})")

                        }

                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = selectedSteps.value,
                    onValueChange = {selectedSteps.value = it},
                    label = { Text(text = "Steps")},
                    modifier = Modifier.fillMaxWidth(0.9f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    shape = CircleShape,
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    onClick = {
                        coroutineScope.launch {
                            try{
                                val steps = selectedSteps.value.toInt()
                                activityViewModel.saveActivity(
                                    ActivityData(
                                        date = curHistory.date,
                                        goalName = selectedGoalName.value,
                                        goalTarget = selectedGoalTarget.value,
                                        steps = steps
                                    )
                                )
                                onBackPress()
                            } catch (e: Exception){
                                selectedSteps.value = "${ curHistory.steps }"
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message ="Steps can only be a numeric value",
                                    duration = SnackbarDuration.Short
                                )
                            }

                        }
                    },
                    shape = CircleShape,
                    contentPadding = PaddingValues(18.dp),
                    colors = ButtonDefaults.buttonColors(ButtonColor),
                ) {
                    Text("Save",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                }
            }



        }
    }
}