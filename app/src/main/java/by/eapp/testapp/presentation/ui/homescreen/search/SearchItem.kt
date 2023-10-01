package by.eapp.testapp.presentation.ui.homescreen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import by.eapp.testapp.R
import by.eapp.testapp.feature_images.domain.model.image_Search.Photo
import by.eapp.testapp.presentation.ui.navigation.BottomNavigationItem
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchImageItem(
    photo: Photo,
    navController: NavController
) {
    val height = Random.nextInt(150, 300).dp
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(height),
        shape = RoundedCornerShape(12.dp),
        onClick = {
            navController.navigate(BottomNavigationItem.Details.wihArgs(photo.id))
        }
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photo.src.large2x)
                    .placeholder(R.drawable.img_1)
                    .crossfade(1000)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = photo.alt,
                modifier = Modifier.fillMaxWidth()
            )


        }
    }
}