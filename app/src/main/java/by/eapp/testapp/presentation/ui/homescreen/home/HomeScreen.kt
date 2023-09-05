package by.eapp.testapp.presentation.ui.homescreen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import by.eapp.testapp.model.chips.CheepsScreen
import by.eapp.testapp.model.imageList.Image
import by.eapp.testapp.presentation.ui.homescreen.search.Searchbar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = hiltViewModel<ImagesListViewModel>()
    val images = viewModel.curatedImages().collectAsLazyPagingItems()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Searchbar(navController = navController)
        CheepsScreen(navController = navController)
        HomeScreenListContent(items = images, navController = navController)
    }
}

@Composable
fun HomeScreenListContent(
    items: LazyPagingItems<Image>,
    navController: NavController
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        contentPadding = PaddingValues(all = 8.dp)
    ) {
        items(
            items = items,
            key = { photo ->
                photo.id
            }
        ) { photo ->
            photo?.let { CardItem(navController = navController, image = photo) }
        }
        val loadState = items.loadState.mediator
        item {
            if (loadState?.refresh is LoadState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(vertical = 20.dp)
                    ) {
                        Text(
                            text = "Loading",
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        LinearProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                }
            }
            if (loadState?.append == LoadState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    LinearProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
            }
            if (loadState?.refresh is LoadState.Error || loadState?.append is LoadState.Error) {
                val isPaginatingError =
                    (loadState.append is LoadState.Error) || items.itemCount > 1
                val error = if (loadState.append is LoadState.Error) {
                    (loadState.append as LoadState.Error).error
                } else {
                    (loadState.refresh as LoadState.Error).error
                }
                val modifier = if (isPaginatingError) {
                    Modifier.padding(8.dp)
                } else {
                    Modifier.fillMaxSize()
                }
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!isPaginatingError) {
                        Icon(
                            modifier = Modifier.size(64.dp),
                            imageVector = Icons.Rounded.Warning,
                            contentDescription = null
                        )
                    }
                    Button(
                        onClick = {
                            items.refresh()
                        },
                        content = {
                            Text(text = "Refresh")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        )
                    )
                }
            }
        }
    }
}



