package com.example.keepfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keepfit.data.entity.ActivityData
import com.example.keepfit.ui.CalendarInput
import com.example.keepfit.ui.KeepFitApp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KeepFitApp()
        }
    }
}






@Composable
fun HistoryRecordingScreen(){
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
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_calendar_month_24),
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
                    .padding(top = 10.dp),
                horizontalAlignment = CenterHorizontally

            ) {
                //ProgressBar(goalSteps = Activity().steps, goalTarget = 6000)
            }
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
                    .padding(top = 20.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Column(
                    modifier = Modifier
                        .padding(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Goal",
                        fontSize = 16.sp,
                        color = Color(0xFF689F38),
                        fontWeight = FontWeight.Bold
                    )
                    Text("Ambitious",
                        fontSize = 18.sp,
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
                        fontSize = 16.sp,
                        color = Color(0xFF5C6BC0),
                        fontWeight = FontWeight.Bold
                    )
                    Text("6000",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text("steps",
                        fontSize = 13.sp,
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
                        fontSize = 16.sp,
                        color = Color(0xFFD32F2F),
                        fontWeight = FontWeight.Bold
                    )

                    Text("5700",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text("steps",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFAAAAAA)
                    )
                }
            }

        }

        Column(
            modifier = Modifier
                .padding(start = 20.dp, top = 16.dp, bottom = 5.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text("Edit Record",
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
                    .padding(start = 20.dp, top = 12.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    "Goal",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        "Ambitious",
                        modifier = Modifier.padding(end = 5.dp),
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_forward_ios_24),
                        modifier = Modifier.size(14.dp),
                        contentDescription = "changeGoal",
                        tint = Color(0xFF919090)
                    )
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 60.dp, bottom = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    "Steps",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        "5700 ",
                        modifier = Modifier.padding(end = 5.dp),
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        "steps ",
                        fontSize = 16.sp,
                        color = Color(0xFF919090)
                    )
                }

            }

        }

    }
}
