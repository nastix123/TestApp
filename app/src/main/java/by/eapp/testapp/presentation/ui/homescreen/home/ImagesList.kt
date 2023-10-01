package by.eapp.testapp.presentation.ui.homescreen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import by.eapp.testapp.feature_images.domain.model.image_List.Image

@Composable
fun ImagesList(
    items:LazyPagingItems<Image>,
    navController: NavController
) {
    val imagesViewModel = hiltViewModel<ImagesListViewModel>()
    val images = imagesViewModel.curatedImages().collectAsLazyPagingItems()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(
            items = items,
            key = { image ->
                image.id

            }
        ) { image ->
            image?.let { CardItem(navController = navController, image = image) }
        }
    }

}