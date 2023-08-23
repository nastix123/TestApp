package by.eapp.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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