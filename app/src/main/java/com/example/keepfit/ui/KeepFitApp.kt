package com.example.keepfit.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.keepfit.*
import com.example.keepfit.R
import com.example.keepfit.ui.Goal.Goal
import com.example.keepfit.ui.History.History
import com.example.keepfit.ui.Activity.Activity

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun KeepFitApp(){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
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
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ){
        Navigation(navController = navController)
    }

}



@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Activity.route){
        composable(route = Screen.Activity.route){
            Activity()
        }
        composable(route = Screen.Goal.route){
            Goal()
        }
        composable(route = Screen.History.route){
            History()
        }

    }
}


@Composable
fun TopBar(
    title: String,
    menu:Boolean = false,
    onClick: () -> Unit = {/*TODO if menu is true*/}
){
    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
    ){
        TopAppBar(
            title = { Text(text = title)},
            backgroundColor = Color.White,
            elevation = 5.dp,
            contentColor = Color.Black,
            actions = {
                if (menu){
                    IconButton(onClick = onClick ) {
                        Icon(
                            modifier = Modifier.padding(horizontal = 10.dp),
                            imageVector = Icons.Default.Menu,
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
            title = { Text(text = title)},
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
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
){
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
