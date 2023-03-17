package com.example.keepfit.ui.goal

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
import com.example.keepfit.data.entity.GoalData
import com.example.keepfit.ui.BottomNavigationBar
import com.example.keepfit.ui.EditTopBar
import com.example.keepfit.ui.theme.ButtonColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddGoalScreen(
    bottomNavController: NavController,
    onBackPress: () -> Unit,
    goalName: String = "",
    goalTarget: String = ""
){
    val curGoalName = rememberSaveable { mutableStateOf(goalName) }
    val curGoalTarget = rememberSaveable { mutableStateOf(goalTarget)}

    val viewModel: GoalViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = { EditTopBar(title = "Add Goal", back = true, onBackPress = onBackPress) },
        bottomBar = {
            BottomNavigationBar(
                onItemClick = { bottomNavController.navigate(it.route) },
                navController = bottomNavController
            )
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = curGoalName.value,
                onValueChange = {goal-> curGoalName.value = goal},
                label = {Text(text = "Add Goal Name")},
                modifier = Modifier.fillMaxWidth(0.9f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                shape = CircleShape,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = curGoalTarget.value,
                onValueChange = {target-> curGoalTarget.value = target},
                label = { Text(text = "Add Goal Target")},
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
                            try {
                                val target = curGoalTarget.value.toInt()
                                var snack = false
                                viewState.goals.forEach {
                                    snack = it.goalName == curGoalName.value
                                }
                                if (!snack){
                                    viewModel.addGoal(
                                        GoalData(
                                            goalName = curGoalName.value,
                                            goalTarget = target
                                        )
                                    )
                                    onBackPress()
                                } else{
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message ="Goal name already exist",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            } catch (e: Exception){
                                curGoalTarget.value = ""
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message ="Goal target can only be a numeric value",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }
                    },
                shape = CircleShape,
                contentPadding = PaddingValues(18.dp),
                colors = ButtonDefaults.buttonColors(ButtonColor),
            ) {
                Text("Add Goal",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        }
    }
}