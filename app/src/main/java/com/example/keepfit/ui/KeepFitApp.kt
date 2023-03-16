package com.example.keepfit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.keepfit.R
import com.example.keepfit.ui.goal.Goal
import com.example.keepfit.ui.history.History
import com.example.keepfit.ui.activity.Activity

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
    onClick: () -> Unit = {/*TODO if menu is true*/}
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
            actions = {
                if (settings){
                    Text(text = "SETTINGS", fontWeight = FontWeight.Bold)
                    IconButton(onClick = onClick ) {
                        Icon(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            imageVector = Icons.Default.Settings,
                            contentDescription = "menu",
                            tint = Color.Black)
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
