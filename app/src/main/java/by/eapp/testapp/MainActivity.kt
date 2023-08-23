package by.eapp.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import by.eapp.testapp.navigation.BottomNavigationScreen
import by.eapp.testapp.ui.theme.TestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TestAppTheme {

                Surface(
                    color = Color.White
                ) {
                    BottomNavigationScreen()
                }

            }
        }
    }
}
