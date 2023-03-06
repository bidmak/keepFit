package com.example.keepfit.ui.History

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun History(){
    HistoryScreen()
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
