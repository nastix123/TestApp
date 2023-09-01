
package by.eapp.testapp.presentation.ui.homescreen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.eapp.testapp.R


@Composable
fun Searchbar(
    navController: NavController
) {
    val viewModel:SearchBarViewModel = hiltViewModel()
    var message by remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(16.dp))
        TextField(
            value = message,
            onValueChange = {
                message = it
                viewModel.searchImages(message)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(1.4.dp)
                        .width(16.dp)
                        .height(16.dp),
                    tint = Color(57, 57, 57, 1)
                )
            },
            placeholder = {
                Text(stringResource(R.string.place_holder))
            },
            modifier = Modifier
                .padding(0.5.dp)
                .fillMaxWidth(0.9f)
                .height(50.dp)
                .clip(shape = RoundedCornerShape(50.dp)),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,

            )
        )
    }
    val response = viewModel.imageList.value
    if (response.data.isEmpty() && response.err.isEmpty()) {
        //EmptyResultScreen()

    }
    if (response.err.isNotEmpty()) {
        //Error result screen
    }
    if (response.isLoad) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))

        }

    }
    if (response.data.isNotEmpty()) {
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2)) {
            items(response.data) {image ->
                SearchImageItem(photo = image, navController = navController)
            }
        }
    }
}



