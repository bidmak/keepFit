package com.example.keepfit.ui.Activity

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.lifecycle.viewmodel.compose.viewModel
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.keepfit.data.entity.ActivityData
import com.example.keepfit.data.entity.GoalData
import com.example.keepfit.ui.EditTopBar
import com.example.keepfit.ui.Goal.GoalViewModel
import com.example.keepfit.ui.Goal.GoalViewState
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun EditGoalScreen(
    navController: NavController,
    viewModel: GoalViewModel = viewModel(),
    activityViewModel: ActivityViewModel = viewModel()
){
    val viewState by viewModel.state.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    val selectedGoal = rememberSaveable { mutableStateOf("")}
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .background(Color(0xFFF5F5F5))
        ) {

            EditTopBar(title = "Edit Goal", back = true, onBackPress = {navController.navigate("homeScreen")})
            Box(modifier = Modifier
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 40.dp),
                    horizontalArrangement = Arrangement.Center

                ) {
                    Text(
                        "Today",
                        modifier = Modifier.padding(horizontal = 5.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF969696),
                    )
                    Text("${Date().time.toDayString()}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 14.dp, vertical = 4.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    "Select a goal",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(30.dp))


            var expanded by remember { mutableStateOf(false) }
            var getGoal = GoalData("",0)

            val icon = if(expanded){
                Icons.Filled.ArrowDropUp
            } else {
                Icons.Filled.ArrowDropDown
            }

            Column {
                OutlinedTextField(
                    value = selectedGoal.value,
                    onValueChange = {selectedGoal.value = it},
                    modifier = Modifier.fillMaxWidth(),
                    label = {Text(text = "Goal")},
                    readOnly = true,
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
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    viewState.goals.forEach { curGoal ->
                        DropdownMenuItem(onClick = {
                            selectedGoal.value = curGoal.goalName
                            expanded = false
                        }) {
                            Text(text = curGoal.goalName)
                            getGoal = curGoal
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier.fillMaxWidth(0.8f),
                onClick = {
                    coroutineScope.launch {
                        activityViewModel.saveActivity(
                            ActivityData(
                                date = Date().time.toDayString(),
                                goalName = getGoal.goalName,
                                goalTarget = getGoal.goalTarget,
                                steps = 0
                            )
                        )
                        // activityViewModel.getSteps(Date().time.toDayString())
                        navController.navigate("homeScreen")
                    }
                          },
                shape = CircleShape,
                contentPadding = PaddingValues(14.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF5C6BC0)),
            ) {
                Text("Save Goal",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }

        }
    }
}


@Composable
fun GoalListDropDown(
    viewState: GoalViewState,
    goal: MutableState<String>
){
    var expanded by remember { mutableStateOf(false) }
    var getGoal: GoalData

    val icon = if(expanded){
        Icons.Filled.ArrowDropUp
    } else {
        Icons.Filled.ArrowDropDown
    }

    Column {
        OutlinedTextField(
            value = goal.value,
            onValueChange = {goal.value = it},
            modifier = Modifier.fillMaxWidth(),
            label = {Text(text = "Goal")},
            readOnly = true,
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
            modifier = Modifier.fillMaxWidth()
        )
        {
            viewState.goals.forEach { curGoal ->
                DropdownMenuItem(onClick = {
                    goal.value = curGoal.goalName
                    expanded = false
                }) {
                    Text(text = curGoal.goalName)
                    getGoal = curGoal
                }
            }
        }
    }
}

fun getCurGoal(curGoal: GoalData): GoalData{
    return curGoal
}