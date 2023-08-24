package by.eapp.testapp.presentation.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(
    var route: String,
    var icon: ImageVector?,
    var title: String
) {
    object Home : BottomNavigationItem("home", Icons.Filled.Home, "Home")
    object Details: BottomNavigationItem("details", null,"Details")
    object Bookmarks : BottomNavigationItem("bookmarks", Icons.Filled.Favorite, "Bookmarks")


}