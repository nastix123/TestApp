package by.eapp.testapp.model.chips

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.eapp.testapp.presentation.ui.navigation.BottomNavigationItem


@Composable
fun CheepsScreen(navController: NavController) {
    val viewModel = hiltViewModel<ChipsViewModel>()
    var selectedChipIndex by remember { mutableStateOf(0) }
    var chipItems by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(viewModel) {

        val collectionList = viewModel.getCollections()
        chipItems = collectionList
    }

    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        items(chipItems) { chipText ->
            val isSelected = chipText == chipItems[selectedChipIndex]
            Chip(
                name = chipText,
                modifier = Modifier.fillMaxWidth(),
                isSelected = isSelected,
                onSelectionChanged = {
                    selectedChipIndex = chipItems.indexOf(chipText)
                },
                navController = navController
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun Chip(
    name: String = "Chip",
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {},
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .height(38.dp)
            .clickable{
                      navController.navigate(BottomNavigationItem.Search.route)
            },
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) Color(0xFFBB1020) else Color(0xFF393939)
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectionChanged(name)
                }
            )
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
