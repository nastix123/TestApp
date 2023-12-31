package by.eapp.testapp.presentation.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

import by.eapp.testapp.presentation.ui.SplashScreen
import by.eapp.testapp.presentation.ui.cardInformation.PhotoDetailsScreen
import by.eapp.testapp.presentation.ui.homescreen.home.HomeScreen


@Composable
fun Navigation(navController: NavHostController,
) {
    NavHost(navController, startDestination = BottomNavigationItem.Splash.route) {
        composable(BottomNavigationItem.Splash.route) {
            Log.d("Navigation", "Navigating to Splash")
            SplashScreen(navController = navController)
        }
        composable(BottomNavigationItem.Home.route) {
            Log.d("Navigation", "Navigating to HomeScreen")
            HomeScreen(navController = navController)

        }
        composable(BottomNavigationItem.Bookmarks.route) {
            Log.d("Navigation", "Navigating to TrainingListScreen")
            //BookmarkScreen()
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