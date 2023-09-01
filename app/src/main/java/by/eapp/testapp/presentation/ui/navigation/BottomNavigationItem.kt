package by.eapp.testapp.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.content.ContextCompat
import by.eapp.testapp.R
import android.content.Context
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search


sealed class BottomNavigationItem(
    var route: String,
    var icon: ImageVector?,
    var title: String
) {
    object Search: BottomNavigationItem("search", Icons.Default.Search,"Search")
    object Splash: BottomNavigationItem("splash", Icons.Filled.Clear,"Splash")
    object Home : BottomNavigationItem("home", Icons.Default.Home, "Home")
    object Details: BottomNavigationItem("details", null,"Details")
    object Bookmarks : BottomNavigationItem("bookmarks", Icons.Filled.Favorite, "Bookmarks")
    object Chips:BottomNavigationItem("chips", null, "Chips")

    fun wihArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }


}