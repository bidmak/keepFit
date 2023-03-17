package com.example.keepfit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.keepfit.PreferenceViewModel
import com.example.keepfit.R
import com.example.keepfit.data.entity.PreferenceData
import com.example.keepfit.ui.goal.Goal
import com.example.keepfit.ui.history.History
import com.example.keepfit.ui.activity.Activity
import com.example.keepfit.ui.theme.ButtonColor
import kotlinx.coroutines.launch

@Composable
fun KeepFitApp(){
    val bottomNavController = rememberNavController()

    NavHost(navController = bottomNavController, startDestination = Screen.Activity.route){
        composable(route = Screen.Activity.route){
            Activity(
                bottomNavController = bottomNavController
            )
        }
        composable(route = Screen.Goal.route){
            Goal(
                bottomNavController = bottomNavController
            )
        }
        composable(route = Screen.History.route){
            History(
                bottomNavController = bottomNavController
            )
        }

    }

}



@Composable
fun TopBar(
    title: String,
    settings:Boolean = false,
    editHistory: Boolean = false,
    editGoal: Boolean = false,
    show: Boolean = false,
    showId: Int = 0
){
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
    ){
        TopAppBar(
            title = { Text(text = title, fontSize = 20.sp)},
            backgroundColor = Color.White,
            elevation = 5.dp,
            contentColor = Color.Black,
            actions = {
                if (settings){
                    Text(text = "SETTINGS", fontWeight = FontWeight.Bold)
                    IconButton(onClick = {expanded = true} ) {
                        Icon(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            imageVector = Icons.Default.Settings,
                            contentDescription = "menu",
                            tint = Color.Black)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        if (editGoal){
                            DropdownMenuItem(
                                onClick = {  }
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = CenterVertically
                                ){
                                    Text(
                                        text = "Edit Goals",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                    ToggleButton (show, showId)
                                }

                            }
                        }
                        if (editHistory){
                            DropdownMenuItem(
                                onClick = {  }
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = CenterVertically
                                ){
                                    Text(
                                        text ="Edit History",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp)
                                    ToggleButton (show, showId)
                                }
                            }
                        }
                    }

                }
            }
        )
    }
}


@Composable
fun EditTopBar(
    title: String,
    back:Boolean = false,
    onBackPress: () -> Unit = {/*TODO if back is true*/}
){
    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
    ){
        TopAppBar(
            title = { Text(text = title, fontSize = 20.sp)},
            backgroundColor = Color.White,
            elevation = 5.dp,
            contentColor = Color.Black,
            navigationIcon = {
                if (back){
                    IconButton(onClick = onBackPress ) {
                        Icon(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "menu",
                            tint = Color.Black)
                    }
                }
            }
        )
    }
}


@Composable
fun BottomNavigationBar(
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
){
    val items = listOf(
        BottomNavItem(
            name = Screen.Activity.route,
            route = Screen.Activity.route,
            icon = Icons.Default.Home
        ),
        BottomNavItem(
            name = Screen.Goal.route,
            route = Screen.Goal.route,
            icon = Icons.Default.Add
        ),
        BottomNavItem(
            name = Screen.History.route,
            route = Screen.History.route,
            icon = ImageVector.vectorResource(id = R.drawable.baseline_history_24)
        ),
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = Modifier
            .shadow(elevation = 10.dp),
        backgroundColor = Color.White
    ) {
        items.forEach{ item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                onClick = { onItemClick(item)},
                icon = {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.name
                        )
                        if(selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }

                }
            )
        }
    }
}


@Composable
fun ToggleButton (
    show: Boolean,
    showId: Int
){

    val preferenceViewModel: PreferenceViewModel = viewModel()
    var isToggled by remember { mutableStateOf(show) }

    val coroutineScope = rememberCoroutineScope()

    Box(
        contentAlignment = Center,
        modifier = Modifier.padding(start = 14.dp)
    ){
        Card(
            shape = RoundedCornerShape(39.dp),
            elevation = 0.dp
        ) {
            Box(
                modifier = Modifier
                    .background(
                        if (isToggled) ButtonColor else Color.Gray
                    )
                    .clickable(
                        onClick = {
                            isToggled = !isToggled
                            coroutineScope.launch {
                                preferenceViewModel.savePreference(
                                    PreferenceData(
                                        id = showId,
                                        preference = isToggled
                                    )
                                )
                            }
                        }
                    ),
                contentAlignment = Center
            ) {
                if (isToggled){
                    Row(
                        modifier = Modifier.padding(4.dp)
                    ){
                        Text(
                            text = "On",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .align(CenterVertically)
                        )
                        Icon(
                            imageVector = Icons.Default.Circle,
                            contentDescription = "On",
                            tint = Color.White,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                } else{
                    Row(
                        modifier = Modifier.padding(4.dp)
                    ){
                        Icon(
                            imageVector = Icons.Default.Circle,
                            contentDescription = "Off",
                            tint = Color.White,
                        )
                        Text(
                            text = "Off",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .align(CenterVertically)
                        )
                    }
                }
            }
        }
    }

}

