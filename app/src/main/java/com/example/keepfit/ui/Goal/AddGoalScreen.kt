package com.example.keepfit.ui.Goal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keepfit.ui.EditTopBar

@Composable
fun AddGoalScreen(
    onBackPress: () -> Unit
){
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val goalName = rememberSaveable { mutableStateOf("") }
            val goalTarget = rememberSaveable { mutableStateOf("") }
            EditTopBar(title = "Add Goal", back = true, onBackPress = onBackPress)
            Spacer(modifier = Modifier.height(30.dp))
            OutlinedTextField(
                value = goalName.value,
                onValueChange = {goal-> goalName.value = goal},
                label = {Text(text = "Add Goal Name")},
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                shape = CircleShape,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = goalTarget.value,
                onValueChange = {target-> goalTarget.value = target},
                label = { Text(text = "Add Goal Target")},
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                shape = CircleShape,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier.fillMaxWidth(0.8f),
                onClick = onBackPress,
                shape = CircleShape,
                contentPadding = PaddingValues(14.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF5C6BC0)),
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