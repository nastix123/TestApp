package by.eapp.testapp.presentation.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavigationItem.Home.route) {
        composable(BottomNavigationItem.Home.route) {
            Log.d("Navigation", "Navigating to HomeScreen")

        }
        composable(BottomNavigationItem.Bookmarks.route) {
            Log.d("Navigation", "Navigating to TrainingListScreen")
        }
    }
}