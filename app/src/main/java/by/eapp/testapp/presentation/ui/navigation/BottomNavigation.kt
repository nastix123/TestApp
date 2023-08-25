

package by.eapp.testapp.presentation.ui.navigation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
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
import androidx.compose.material3.Scaffold
import androidx.navigation.NavType
import androidx.navigation.navArgument

import by.eapp.testapp.presentation.ui.homescreen.home.HomeScreen



@Composable
fun BottomNavigationScreen() {
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { BottomNavigation(navController = navController) },
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
        BottomNavigationItem.Bookmarks
    )
    val navController = rememberNavController()

    Scaffold(

        bottomBar = {
            androidx.compose.material.BottomNavigation(backgroundColor = colorResource(id = R.color.white)) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { item ->
                    BottomNavigationItem(
                        icon = { item.icon?.let { Icon(imageVector = it, contentDescription = null) } },

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
           /* composable(BottomNavigationItem.Splash.route) {
                SplashScreen(navController = navController)
            }*/
            composable(BottomNavigationItem.Bookmarks.route) {
                HomeScreen()
            }
            composable(BottomNavigationItem.Home.route) {
                HomeScreen()
             }
            composable(
                BottomNavigationItem.Details.route+"/{photoId}",
                arguments = listOf(
                    navArgument("photoId") {
                        type = NavType.IntType
                        nullable = false
                    }
                )) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun testNavigation() {
    BottomNavigationScreen()
}