package by.eapp.testapp.uicomponents.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random

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
        height = Random.nextInt(100, 300).dp,
        color = Color(
            Random.nextLong(0xFFFFFFFF)
        ).copy(alpha = 1f)
    )
}