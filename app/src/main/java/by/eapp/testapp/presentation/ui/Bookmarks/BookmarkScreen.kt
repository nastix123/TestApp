package by.eapp.testapp.presentation.ui.Bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import by.eapp.testapp.model.imageDetail.toImageDetailResponse
import by.eapp.testapp.presentation.ui.cardInformation.CardInformation
import by.eapp.testapp.presentation.ui.cardInformation.CardScreenViewModel
import by.eapp.testapp.presentation.ui.homescreen.home.CardItem

@Composable
fun BookmarkScreen(navController: NavController) {

    val viewModel = hiltViewModel<CardScreenViewModel>()
    val listOfFavoriteImages = viewModel.listOfFavoriteImages.collectAsState(emptyList())
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
        )
        Text(
            text = "Bookmarks",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                color = Color.White
            )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(26.dp)
        )
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        )
        {
            items(listOfFavoriteImages.value) { favoriteImage ->
                CardInformation(image = favoriteImage.toImageDetailResponse())
            }
        }

    }
}
/*
@Composable
fun CardItemForBookmarks(
    navController: NavController,
    image
) {

}*/

