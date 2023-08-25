package by.eapp.testapp.presentation.ui.homescreen.home

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import by.eapp.testapp.data.db.database.imageList.Image
import by.eapp.testapp.presentation.ui.homescreen.search.Searchbar


@Composable
fun MainChip(
    modifier: Modifier = Modifier
) {
    ElevatedSuggestionChip(
        modifier = Modifier
            .height(38.dp)
            .background(color = Color(0xFFF3F5F9), shape = RoundedCornerShape(size = 100.dp))
            .padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 10.dp)
            .clickable { }
            .then(Modifier.wrapContentWidth()),
        onClick = {},
        label = {
            Text(
                text = "Watches",
                style = TextStyle(
                    fontSize = 7.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF1E1E1E),
                    letterSpacing = 0.28.sp,
                )
            )
        }
    )
}


@Preview
@Composable
fun testChip() {
    MainChip()
}

@Composable
fun ChipRow() {
    LazyRow {
        items(7) {
            MainChip()
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun HomeScreen() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Searchbar()
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth()
        )
        ChipRow()
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .fillMaxWidth()
        )


    }
}

@Composable
fun HomeScreenListContent(
    items: LazyPagingItems<Image>,
    navController: NavController
) {
    val photosViewModel = hiltViewModel<ImagesListViewModel>()

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
                            text = "Fetching Data",
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        CircularProgressIndicator(
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
                    CircularProgressIndicator(
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

                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = error.localizedMessage ?: "Makosa imefanyika",
                        textAlign = TextAlign.Center
                    )

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



