package com.example.keepfit.ui.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keepfit.ui.EditTopBar

@Composable
fun EditGoalScreen(
    onBackPress: () -> Unit
){
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .background(Color(0xFFF5F5F5))
        ) {

            EditTopBar(title = "Edit Goal", back = true, onBackPress = onBackPress)
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
                    Text("Feb 21, 2023", fontSize = 16.sp, fontWeight = FontWeight.Bold)
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

            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 1.dp)
                    .fillMaxWidth()
                    .shadow(elevation = 1.dp, shape = RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.99f)
                        .padding(horizontal = 14.dp, vertical = 12.dp),
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
                            imageVector = Icons.Default.ArrowBack,
                            modifier = Modifier.size(14.dp),
                            contentDescription = "changeGoal",
                            tint = Color(0xFF919090)
                        )
                    }

                }


            }

        }
    }
}