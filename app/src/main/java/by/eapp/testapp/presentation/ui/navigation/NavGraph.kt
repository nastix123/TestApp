package by.eapp.testapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import by.eapp.testapp.presentation.ui.cardInformation.CardInformation
import by.eapp.testapp.presentation.ui.cardInformation.PhotoDetailsScreen
import by.eapp.testapp.presentation.ui.homescreen.home.HomeScreen
import by.eapp.testapp.presentation.ui.homescreen.search.Searchbar

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationItem.Home.route
    ) {
        composable(BottomNavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(BottomNavigationItem.Search.route) {
            Searchbar(navController = navController)
        }

        composable(
            route = BottomNavigationItem.Details.route + "/{photoId}",
            arguments = listOf(
                navArgument("photoId") {
                    type = NavType.IntType
                    nullable = false
                }
            )

        ) { entry ->
            PhotoDetailsScreen(
                imageId = entry.arguments?.getInt("photoId")!!
            )
        }
    }
}