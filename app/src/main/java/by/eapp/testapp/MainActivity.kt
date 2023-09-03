package by.eapp.testapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import by.eapp.testapp.presentation.ui.SplashScreen
import by.eapp.testapp.presentation.ui.navigation.BottomNavigation
import by.eapp.testapp.presentation.ui.navigation.BottomNavigationScreen
//import by.eapp.testapp.presentation.ui.navigation.BottomNavigationScreen
import by.eapp.testapp.ui.theme.TestAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp




@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TestAppTheme {
                val navController = rememberNavController()
                Surface {
                  BottomNavigation(navController = navController)
                }

            }
        }
    }
}

