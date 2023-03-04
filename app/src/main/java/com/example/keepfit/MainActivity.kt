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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
fun AddSteps(
){

}fun percent(stepsAdded: Int, steps: Int){
    var activity = Activity()
    var curSteps = activity.steps + stepsAdded
    var percentage = steps/activity.goalTarget


}


@Composable
fun EditTopBa(
    title: String,
    navigationIcon: Boolean = false,
    iconMenu: Boolean = false,
    actions: Boolean = false
){
    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
    ){
        TopAppBar(
            backgroundColor = Color.White,
            elevation = 14.dp,
            contentColor = Color.Black,
            title = {
                Text(modifier = Modifier.padding(start = 10.dp),
                    text = title
                )
            },
            navigationIcon = {
                if (navigationIcon){
                    Icon(
                        modifier = Modifier.padding(start = 10.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = "cancel",
                        tint = Color.Black)
                }
                if (iconMenu){
                    Icon(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        imageVector = Icons.Default.Menu,
                        contentDescription = "menu",
                        tint = Color.Black)
                }
            },
            actions = {
                if (actions){
                    Text(
                        modifier = Modifier.padding(end =10.dp),
                        text = "Add",
                        fontSize = 16.sp
                    )
                }
            }
        )
    }

}


@Composable
fun GoalEditScreen() {

}

@Composable
fun GoalSettingsScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFA)),
        contentAlignment = Alignment.Center
    ){
        Text("Goal settings screen")
    }
}


private const val  CALENDAR_ROWS = 5
private const val  CALENDAR_COLUMNS = 7

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    calendarInput: List<CalendarInput>,
    onDayClick:(Int)->Unit,
    strokeWidth:Float = 15f,
    month:String
) {

    var canvasSize by remember {
        mutableStateOf(Size.Zero)
    }
    var clickAnimationOffset by remember {
        mutableStateOf(Offset.Zero)
    }

    var animationRadius by remember {
        mutableStateOf(0f)
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = month,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 40.sp
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {
                    detectTapGestures(
                        onTap = { offset ->
                            val column =
                                (offset.x / canvasSize.width * CALENDAR_COLUMNS).toInt() + 1
                            val row = (offset.y / canvasSize.height * CALENDAR_ROWS).toInt() + 1
                            val day = column + (row - 1) * CALENDAR_COLUMNS
                            if (day <= calendarInput.size) {
                                onDayClick(day)
                                clickAnimationOffset = offset
                                scope.launch {
                                    animate(0f, 225f, animationSpec = tween(300)) { value, _ ->
                                        animationRadius = value
                                    }
                                }
                            }

                        }
                    )
                }
        ){
            val canvasHeight = size.height
            val canvasWidth = size.width
            canvasSize = Size(canvasWidth,canvasHeight)
            val ySteps = canvasHeight/ CALENDAR_ROWS
            val xSteps = canvasWidth/ CALENDAR_COLUMNS

            val column = (clickAnimationOffset.x / canvasSize.width * CALENDAR_COLUMNS).toInt() + 1
            val row = (clickAnimationOffset.y / canvasSize.height * CALENDAR_ROWS).toInt() + 1

            val path = Path().apply {
                moveTo((column-1)*xSteps,(row-1)*ySteps)
                lineTo(column*xSteps,(row-1)*ySteps)
                lineTo(column*xSteps,row*ySteps)
                lineTo((column-1)*xSteps,row*ySteps)
                close()
            }

            clipPath(path){
                drawCircle(
                    brush = Brush.radialGradient(
                        listOf(Color.Blue.copy(0.8f), Color.Blue.copy(0.2f)),
                        center = clickAnimationOffset,
                        radius = animationRadius + 0.1f
                    ),
                    radius = animationRadius + 0.1f,
                    center = clickAnimationOffset
                )
            }

            drawRoundRect(
                Color.Blue,
                cornerRadius = CornerRadius(25f,25f),
                style = Stroke(
                    width = strokeWidth
                )
            )

            for(i in 1 until CALENDAR_ROWS){
                drawLine(
                    color = Color.Blue,
                    start = Offset(0f,ySteps*i),
                    end = Offset(canvasWidth, ySteps*i),
                    strokeWidth = strokeWidth
                )
            }
            for(i in 1 until CALENDAR_COLUMNS){
                drawLine(
                    color = Color.Blue,
                    start = Offset(xSteps*i,0f),
                    end = Offset(xSteps*i, canvasHeight),
                    strokeWidth = strokeWidth
                )
            }
            val textHeight = 17.dp.toPx()
            for(i in calendarInput.indices){
                val textPositionX = xSteps * (i% CALENDAR_COLUMNS) + strokeWidth
                val textPositionY = (i / CALENDAR_COLUMNS) * ySteps + textHeight + strokeWidth/2
                drawContext.canvas.nativeCanvas.apply {
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
                    .padding(top = 20.dp),
                horizontalAlignment = CenterHorizontally

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
            horizontalAlignment = CenterHorizontally,
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
