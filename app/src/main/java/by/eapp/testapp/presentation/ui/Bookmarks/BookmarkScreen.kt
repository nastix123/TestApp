package by.eapp.testapp.presentation.ui.Bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import by.eapp.testapp.presentation.ui.homescreen.RandomColorBox
/*
@Composable
fun BookmarkScreen(navController:NavController) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxWidth().height(26.dp))
    Text(text = "Bookmarks",
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight(700),
            color = Color.White
        ) )
        Spacer(modifier = Modifier.fillMaxWidth().height(26.dp))
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    )
    {
        items(by.eapp.testapp.presentation.ui.homescreen.home.items) { item ->
            RandomColorBox(item = item, navController = navController,photo = Imag)
        }
    }

}
}


fun <T: Any> LazyGridScope.items(

)
*/