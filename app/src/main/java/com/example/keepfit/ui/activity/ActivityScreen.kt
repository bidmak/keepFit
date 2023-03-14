package com.example.keepfit.ui.activity

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.keepfit.data.entity.ActivityData
import com.example.keepfit.ui.Screen
import com.example.keepfit.ui.TopBar
import com.example.keepfit.ui.theme.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


fun Long.toDayString(): String {
    return SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date(this))
}

suspend fun updateAct(viewModel: ActivityViewModel, curActivity: ActivityData, addedSteps: Int){
    val date = Date().time.toDayString()
    viewModel.saveActivity(
        ActivityData(
            date = date,
            goalName = curActivity.goalName,
            goalTarget = curActivity.goalTarget,
            steps = curActivity.steps + addedSteps
        )
    )
}

var activeGoal: String = ""


@Composable
fun ActivityScreen(
    navController: NavController
){
    val viewModel: ActivityViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()

    val date = Date().time.toDayString()
    val curActivity = viewState.activities.firstOrNull { activity -> activity.date == date } ?: ActivityData(
        date = date,
        goalName = "Goal",
        goalTarget = 6000,
        steps = 0
    )

    activeGoal = curActivity.goalName

    val coroutineScope = rememberCoroutineScope()

    var addSteps by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBar("Dashboard")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, end = 20.dp),
            horizontalAlignment = Alignment.End

        ) {
            Text("Today",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = LightGrayColor,
            )
            Text("${curActivity.date}", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                Text("Goal",
                    fontSize = 18.sp,
                    color = Color(0xFF689F38),
                    fontWeight = FontWeight.Bold
                )
                Text("${curActivity.goalName}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Target",
                    fontSize = 18.sp,
                    color = ButtonColor,
                    fontWeight = FontWeight.Bold
                )
                Text("${curActivity.goalTarget}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text("steps",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightGrayColor
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Move",
                    fontSize = 18.sp,
                    color = MoveColor,
                    fontWeight = FontWeight.Bold
                )

                Text(text = "${curActivity.steps}",
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
            ProgressBar(curActivity = curActivity)
        }


        Box(modifier = Modifier
            .shadow(elevation = 4.dp)
            .fillMaxHeight()
            .background(BackgroundColor)) {

            Column(
                modifier = Modifier
                .fillMaxWidth(),
            ) {
                Box(
                    modifier = Modifier
                        .offset(8.dp, 8.dp)
                        .shadow(elevation = 1.dp, shape = RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .fillMaxWidth(0.96f)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(14.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            "Quick Add",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(
                                onClick = {
                                    coroutineScope.launch {
                                        updateAct(
                                            viewModel = viewModel,
                                            curActivity = curActivity,
                                            addedSteps = 50
                                        )
                                    }
                                },
                                enabled = true,
                                shape = CircleShape,
                                contentPadding = PaddingValues(14.dp),
                                colors = ButtonDefaults.buttonColors(AddButtonColor)
                            ) {
                                Text(
                                    "+50",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Button(
                                onClick = {
                                    coroutineScope.launch {
                                        updateAct(
                                            viewModel = viewModel,
                                            curActivity = curActivity,
                                            addedSteps = 100
                                        )
                                    }
                                },
                                enabled = true,
                                shape = CircleShape,
                                contentPadding = PaddingValues(14.dp),
                                colors = ButtonDefaults.buttonColors(AddButtonColor)
                            ) {
                                Text(
                                    "+100",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Button(
                                onClick = {
                                    coroutineScope.launch {
                                        updateAct(
                                            viewModel = viewModel,
                                            curActivity = curActivity,
                                            addedSteps = 200
                                        )
                                    }
                                },
                                enabled = true,
                                shape = CircleShape,
                                contentPadding = PaddingValues(14.dp),
                                colors = ButtonDefaults.buttonColors(AddButtonColor)
                            ) {
                                Text(
                                    "+200",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                            Button(
                                onClick = {
                                    coroutineScope.launch {
                                        updateAct(
                                            viewModel = viewModel,
                                            curActivity = curActivity,
                                            addedSteps = 500
                                        )
                                    }
                                },
                                enabled = true,
                                shape = CircleShape,
                                contentPadding = PaddingValues(14.dp),
                                colors = ButtonDefaults.buttonColors(AddButtonColor)
                            ) {
                                Text(
                                    "+500",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = addSteps,
                            onValueChange = { addedSteps -> addSteps = addedSteps },
                            label = { Text(text = "Add Steps") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    coroutineScope.launch {
                                        val addedSteps = addSteps.toInt()
                                        updateAct(
                                            viewModel = viewModel,
                                            curActivity = curActivity,
                                            addedSteps = addedSteps
                                        )
                                        addSteps = ""
                                    }
                                }
                            ),
                            singleLine = true,
                            shape = CircleShape
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            onClick = {
                                coroutineScope.launch {
                                    val addedSteps = addSteps.toInt()
                                    updateAct(
                                        viewModel = viewModel,
                                        curActivity = curActivity,
                                        addedSteps = addedSteps
                                    )
                                    addSteps = ""
                                }
                            },
                            contentPadding = PaddingValues(14.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(AddButtonColor)
                        ) {
                            Text(
                                "Add",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    onClick = { navController.navigate(route = Screen.EditActivityScreen.passActivity(
                        date = curActivity.date,
                        goalName = curActivity.goalName,
                        goalTarget = curActivity.goalTarget,
                        steps = curActivity.steps
                    )) },
                    shape = CircleShape,
                    contentPadding = PaddingValues(14.dp),
                    colors = ButtonDefaults.buttonColors(ButtonColor)
                ) {
                    Text(
                        "Edit Activity",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }


}


@Composable
fun ProgressBar(
    curActivity: ActivityData,
    number: Int = 100,
    fontSize: TextUnit = 24.sp,
    radius: Dp = 140.dp,
    color: Color = MoveColor,
    strokeWidth: Dp = 16.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    showSteps: Boolean = true,
    percentageColor: Color = ButtonColor
){

    val percentage = curActivity.steps.toFloat()/curActivity.goalTarget

    var animationPlayed by remember {
        mutableStateOf(false)
    }


    val curPercentage = animateFloatAsState(
        targetValue = if(animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(key1 = true){
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(radius * 1.8f)
            .padding(20.dp)
    ){
        Canvas(
            modifier = Modifier.size(radius * 2f)
        ){
            drawArc(
                color = color,
                -90f,
                360 * curPercentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )

        }
        val percentageText = (percentage * number).toInt().toString()

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = ("${percentageText}%"),
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                color = percentageColor
            )
            if(showSteps){
                Text("${curActivity.steps} steps", fontSize = 15.sp,
                    color = LightTextColor,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
}
