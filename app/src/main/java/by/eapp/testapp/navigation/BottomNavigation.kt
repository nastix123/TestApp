package by.eapp.testapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import by.eapp.testapp.R
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost

@Composable
fun BottomNavigationScreen() {
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { BottomNavigation(navController = navController)},
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController)
            }
        },
    )
}

@Composable
fun BottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier) {
    val items = listOf(
       BottomNavigationItem.Home,
    BottomNavigationItem.Bookmarks )
    val navController = rememberNavController()
    Scaffold(
        containerColor = colorResource(id = R.color.purple_200),
        bottomBar = {
            androidx.compose.material.BottomNavigation(backgroundColor = colorResource(id = R.color.white)) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { item ->
                    BottomNavigationItem(
                        icon = { Icon(imageVector = item.icon, contentDescription = null) },
                        label = { Text(item.title) },
                        selected = currentDestination?.hierarchy?.any {
                            it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = BottomNavigationItem.Home.route, Modifier.padding(innerPadding)) {
            composable(BottomNavigationItem.Bookmarks.route) { }
            composable(BottomNavigationItem.Home.route) {  }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun testNavigation() {
    BottomNavigationScreen()
}