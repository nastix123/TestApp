package by.eapp.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import by.eapp.testapp.presentation.ui.SplashScreen
import by.eapp.testapp.presentation.ui.navigation.BottomNavigationScreen
import by.eapp.testapp.ui.theme.TestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            installSplashScreen()
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

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarSample() {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf("") }

    Scaffold() {
        SearchBar(
            query = text,
            onQueryChange = {
                            text = it
            },
            onSearch = {active = false},
            active = active,
            onActiveChange = {
                active  = it
            }
        ) {
        }
    }
}
*/

@Preview
@Composable
fun Search() {

}