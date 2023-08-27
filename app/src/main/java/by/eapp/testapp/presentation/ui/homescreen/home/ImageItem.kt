package by.eapp.testapp.presentation.ui.homescreen.home

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import by.eapp.testapp.R
import by.eapp.testapp.model.imageList.Image
import by.eapp.testapp.presentation.ui.navigation.BottomNavigationItem
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlin.random.Random

@Composable
fun CardItem(
    navController:NavController,
    image: Image

) {
    val height = Random.nextInt(150, 300).dp
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(height)
            .clickable {
                navController.navigate(BottomNavigationItem.Details.wihArgs(image.id)) },
        shape = RoundedCornerShape(12.dp)
    ){
        Box{
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image.src.large2x)
                    .placeholder(R.drawable.img_1)
                    .crossfade(1000)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = image.alt,
                modifier = Modifier.fillMaxWidth()
            )

        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = image.photographer,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
        }
    }
}

fun <T : Any> LazyStaggeredGridScope.items(
    items: LazyPagingItems<T>,
    key: ((item: T) -> Any)? = null,
    itemContent: @Composable LazyStaggeredGridItemScope.(item: T?) -> Unit
) {
    items(
        count = items.itemCount,
        key = if (key == null) {
            null
        } else {
            { index ->
                val item = items.peek(index)
                if (item == null) {
                    PagingPlaceholderKey(index)
                } else {
                    key(item)
                }
            }
        }
    ) { index ->
        itemContent(items[index])
    }
}



public data class PagingPlaceholderKey(private val index: Int) : Parcelable {
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(index)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<PagingPlaceholderKey> =
            object : Parcelable.Creator<PagingPlaceholderKey> {
                override fun createFromParcel(parcel: Parcel) =
                    PagingPlaceholderKey(parcel.readInt())

                override fun newArray(size: Int) = arrayOfNulls<PagingPlaceholderKey?>(size)
            }
    }
}

