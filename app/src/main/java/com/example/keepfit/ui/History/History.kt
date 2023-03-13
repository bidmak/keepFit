package com.example.keepfit.ui.History

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.keepfit.data.entity.ActivityData
import com.example.keepfit.data.entity.GoalData
import com.example.keepfit.rememberKeepFitAppState
import com.example.keepfit.ui.Activity.ActivityViewModel
import com.example.keepfit.ui.Goal.GoalViewModel
import com.example.keepfit.ui.TopBar
import kotlinx.coroutines.launch

@Composable
fun History(){
    HisScreen()
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HisScreen(){
    val viewModel: ActivityViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color(0xFFF1F1F1))
        ) {
            TopBar(title = "History")
            HisList(
                list = viewState.activities
            )
        }
    }
}


@Composable
private fun HisList(
    list: List<ActivityData>
) {
    LazyColumn(
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        items(list){ item ->
            HisListItems(
                activities = item
            )
        }
    }
}


@Composable
private fun HisListItems(
    activities: ActivityData,
    viewModel: ActivityViewModel = viewModel(),
    navController: NavController = rememberKeepFitAppState().navController
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
                        .fillMaxWidth(0.7f),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = activities.date,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = activities.goalName,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "${activities.goalTarget} steps",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = "${activities.steps}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                IconButton(onClick = {
                    navController.navigate("goalScreen") }
                )

                {
                    Icon(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        imageVector = Icons.Default.Edit,
                        contentDescription = "edit",
                        tint = Color.Black)
                }

                IconButton(onClick = {
                    coroutineScope.launch {
                        viewModel.updateActivity(activities)
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



@Composable
fun HistoryScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6E6E6))
    ) {
        Box() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center

            ) {
                Text("Sunday, Feb 26",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = com.example.keepfit.R.drawable.baseline_calendar_month_24),
                    modifier = Modifier.padding(start = 5.dp),
                    contentDescription = "changeGoal",
                    tint = Color(0xFF7E57C2)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                Text("Mon", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Tue", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Wed", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Thu", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Fri", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Sat", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Sun", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Red)
            }
        }

        Box(){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                //ProgressBar(goalSteps = Activity().steps, goalTarget = 6000)
            }
        }

        Column(
            modifier = Modifier
                .padding(start = 20.dp, bottom = 5.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text("Record",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .offset(12.dp)
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(20.dp))
                .background(Color.White)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 40.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Column(
                    modifier = Modifier
                        .padding(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Goal",
                        fontSize = 18.sp,
                        color = Color(0xFF689F38),
                        fontWeight = FontWeight.Bold
                    )
                    Text("Ambitious",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Target",
                        fontSize = 18.sp,
                        color = Color(0xFF5C6BC0),
                        fontWeight = FontWeight.Bold
                    )
                    Text("6000",
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

                Column(
                    modifier = Modifier
                        .padding(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Move",
                        fontSize = 18.sp,
                        color = Color(0xFFD32F2F),
                        fontWeight = FontWeight.Bold
                    )

                    Text("5700",
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
            }

        }

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 28.dp, 0.dp, 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Button(
                modifier = Modifier.fillMaxWidth(0.8f),
                onClick = { /*TODO*/ },
                shape = CircleShape,
                contentPadding = PaddingValues(14.dp),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text("Clear History",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}
