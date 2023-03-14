package com.example.keepfit.ui.activity

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
import com.example.keepfit.data.entity.ActivityData
import com.example.keepfit.ui.EditTopBar
import com.example.keepfit.ui.goal.GoalViewModel
import com.example.keepfit.ui.theme.ButtonColor
import com.example.keepfit.ui.theme.LightTextColor
import kotlinx.coroutines.launch

@Composable
fun EditActivityScreen(
    onBackPress: () -> Unit,
    date: String,
    goalName: String,
    goalTarget: Int,
    steps: Int
){

    val activityViewModel: ActivityViewModel = viewModel()
    val curActivity = ActivityData(
        date = date,
        goalName = goalName,
        goalTarget = goalTarget,
        steps = steps
    )

    val goalViewModel: GoalViewModel = viewModel()
    val goalViewState by goalViewModel.state.collectAsState()

    var selectedGoalName = rememberSaveable { mutableStateOf(curActivity.goalName)}
    val selectedGoalTarget = rememberSaveable { mutableStateOf(curActivity.goalTarget)}

    var expanded by remember { mutableStateOf(false) }

    val icon = if(expanded){
        Icons.Filled.ArrowDropUp
    } else {
        Icons.Filled.ArrowDropDown
    }

    val coroutineScope = rememberCoroutineScope()

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            EditTopBar(title = "Edit Goal", back = true, onBackPress = onBackPress)
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
                    color = LightTextColor,
                )
                Text("${curActivity.date}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            Text(
                "Select a goal",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = selectedGoalName.value,
                    onValueChange = {selectedGoalName.value = it},
                    modifier = Modifier.fillMaxWidth(0.9f),
                    label = {Text(text = "Goal")},
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
                Button(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    onClick = {
                        coroutineScope.launch {
                            activityViewModel.saveActivity(
                                ActivityData(
                                    date = curActivity.date,
                                    goalName = selectedGoalName.value,
                                    goalTarget = selectedGoalTarget.value,
                                    steps = curActivity.steps
                                )
                            )
                            onBackPress()
                        }
                    },
                    shape = CircleShape,
                    contentPadding = PaddingValues(18.dp),
                    colors = ButtonDefaults.buttonColors(ButtonColor),
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
}

