package by.eapp.testapp.presentation.ui.navigation


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.eapp.testapp.R
import by.eapp.testapp.model.chips.CheepsScreen
import by.eapp.testapp.presentation.ui.cardInformation.PhotoDetailsScreen
import by.eapp.testapp.presentation.ui.homescreen.home.HomeScreen
import by.eapp.testapp.presentation.ui.homescreen.search.Searchbar


@Composable
fun BottomNavigationScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController)
            }
        },
        topBar = {

        })

}

@Composable
fun BottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Bookmarks
    )
    val navController = rememberNavController()
    var bottomBarState = remember {
        (mutableStateOf(true))
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    when (navBackStackEntry?.destination?.route) {
        "Home" -> {
            bottomBarState.value = true
        }
        "Bookmarks" -> {
            bottomBarState.value = true
        }
        "Details" -> {
            bottomBarState.value = false
        }
    }
    Scaffold(
        bottomBar = {
            androidx.compose.material.BottomNavigation(backgroundColor = Color(30, 30, 30, 1)) {

                items.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            item.icon?.let {
                                Icon(
                                    imageVector = it,
                                    contentDescription = null
                                )
                            }
                        },
                        selected = currentDestination?.hierarchy?.any {
                            it.route == item.route
                        } == true,
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
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavigationItem.Home.route,
            modifier = modifier.then(Modifier.padding(padding))
        ) {
            composable(BottomNavigationItem.Search.route) {
                Searchbar(navController = navController)
            }
            composable(BottomNavigationItem.Bookmarks.route) {
                HomeScreen(navController)
            }
            composable(BottomNavigationItem.Home.route) {
                HomeScreen(navController)
            }
            composable(BottomNavigationItem.Chips.route) {
                CheepsScreen(navController = navController)
            }
            composable(
                BottomNavigationItem.Details.route + "/{photoId}",
                arguments = listOf(
                    navArgument("photoId") {
                        type = NavType.IntType
                        nullable = false
                    }
                )) { entry ->
                // Отображаем экран PhotoDetailsScreen без BottomNavigation
                PhotoDetailsScreen(imageId = entry.arguments?.getInt("photoId")!!)
            }
        }
    }
}

