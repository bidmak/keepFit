package com.example.keepfit.ui.Home

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
import androidx.navigation.NavController
import com.example.keepfit.*
import com.example.keepfit.ui.TopBar

@Composable
fun HomeScreen(
    activities: Activity,
    navController: NavController
){
    var steps = remember {
        mutableStateOf(activities.steps)
    }

    var addSteps by remember {
        mutableStateOf("")
    }

    var percentage = remember {
        mutableStateOf(activities.percentage)
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
                color = Color(0xFFAAAAAA),
            )
            Text("Feb 21, 2023", fontSize = 14.sp, fontWeight = FontWeight.Bold)
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
                Text("${activities.goalName}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Target",
                    fontSize = 18.sp,
                    color = Color(0xFF5C6BC0),
                    fontWeight = FontWeight.Bold
                )
                Text("${activities.goalTarget}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text("steps",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFAAAAAA)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Move",
                    fontSize = 18.sp,
                    color = Color(0xFFD32F2F),
                    fontWeight = FontWeight.Bold
                )

                Text(text = "${steps.value}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text("steps",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFAAAAAA)
                )
            }
            ProgressBar( goalSteps = steps, percentage = percentage)
        }


        Box(modifier = Modifier
            .shadow(elevation = 4.dp)
            .fillMaxHeight()
            .background(Color(0xFFE6E6E6))) {

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
                                    steps.value += 50
                                    percentage.value = steps.value.toFloat() / activities.goalTarget
                                },
                                enabled = true,
                                shape = CircleShape,
                                contentPadding = PaddingValues(14.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFFB39DDB))
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
                                    steps.value += 100
                                    percentage.value = steps.value.toFloat() / activities.goalTarget
                                },
                                enabled = true,
                                shape = CircleShape,
                                contentPadding = PaddingValues(14.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFFB39DDB))
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
                                    steps.value += 200
                                    percentage.value = steps.value.toFloat() / activities.goalTarget
                                },
                                enabled = true,
                                shape = CircleShape,
                                contentPadding = PaddingValues(14.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFFB39DDB))
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
                                    steps.value += 500
                                    percentage.value = steps.value.toFloat() / activities.goalTarget
                                },
                                enabled = true,
                                shape = CircleShape,
                                contentPadding = PaddingValues(14.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xFFB39DDB))
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
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    steps.value += addSteps.toInt()
                                    percentage.value = steps.value.toFloat() / activities.goalTarget
                                    addSteps = ""
                                    navController.navigate("homeScreen")
                                }
                            ),
                            singleLine = true,
                            shape = CircleShape
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            onClick = {
                                steps.value += addSteps.toInt()
                                percentage.value = steps.value.toFloat() / activities.goalTarget
                                addSteps = ""
                            },
                            contentPadding = PaddingValues(14.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(Color(0xFF5C6BC0))
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
                    modifier = Modifier.fillMaxWidth()
                        .padding(20.dp),
                    onClick = { navController.navigate("editGoalScreen") },
                    shape = CircleShape,
                    contentPadding = PaddingValues(14.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFC2185B))
                ) {
                    Text(
                        "Edit Goal",
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
    goalTarget: Int = Activity().goalTarget,
    goalSteps: MutableState<Int>,
    percentage: MutableState<Float>,
    number: Int = 100,
    fontSize: TextUnit = 24.sp,
    radius: Dp = 140.dp,
    color: Color = Color(0xFFD32F2F),
    strokeWidth: Dp = 16.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
){

    var steps by remember {
        mutableStateOf(goalSteps)
    }
    var target by remember {
        mutableStateOf(goalTarget)
    }

    var percentage by remember {
        mutableStateOf(percentage)
    }

    var animationPlayed by remember {
        mutableStateOf(false)
    }


    var curPercentage = animateFloatAsState(
        targetValue = if(animationPlayed) percentage.value else 0f,
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
        var percentageText = (percentage.value * number).toInt().toString()

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
                color = Color(0xFF5C6BC0)
            )
            Text("${steps.value} steps", fontSize = 15.sp,
                color = Color(0xFF969696),
                fontWeight = FontWeight.Bold)
        }
    }
}
