package by.eapp.testapp.presentation.ui.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPhotoScreen(
    navController: NavController
) {
    val viewModel: SearchPhotosViewModel = hiltViewModel()
    var query by remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it
                    viewModel.searchImages(query)
                },
                enabled = true,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                },
                label = {
                    Text(text = "Search here")
                },
                modifier = Modifier.fillMaxWidth()
            )

            val result = viewModel.imageList.value

            if (result.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Loading...")
                }
            }
            if (result.data.isNotEmpty()) {
                /* LazyColumn {
                     items(result.data.size) {
                         result.data.forEach { photo ->
                             SearchPhotoItem(photo = photo, navController = navController)
                         }
                     }
                 }*/

                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    verticalItemSpacing = 16.dp,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(result.data) {
                        SearchPhotoItem(photo = it, navController = navController)
                    }
                }
            }
            if (result.data.isEmpty() && result.error.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = Icons.Default.Warning, contentDescription = "Warning Icon")
                    Text(text = "No result(s) found")
                }
            }
            if (result.error.isNotEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(imageVector = Icons.Default.Warning, contentDescription = "Warning Icon")
                    Text(text = result.error)
                }
            }
        }
    }
}














@Composable
fun LazyGrid() {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    )
    {
        items(items) { item ->
            RandomColorBox(item = item)
        }
    }
}

val items = (1..100).map {
    GridItem(
        height = Random.nextInt(150, 300).dp,
        color = Color(
            Random.nextLong(0xFFFFFFFF)
        ).copy(alpha = 1f)
    )
}
*/
