package by.eapp.testapp.presentation.ui.cardInformation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import by.eapp.testapp.R
import by.eapp.testapp.func.Resource
import by.eapp.testapp.func.download.ImageDownloader
import by.eapp.testapp.model.imageDetail.ImageDetailResponse
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun CardInformation(
    image: ImageDetailResponse,

    ) {
    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }
    val viewModel = hiltViewModel<CardScreenViewModel>()
    val listOfFavoriteImage by viewModel.listOfFavoriteImages.collectAsState(emptyList())

    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color(30, 30, 30, 1))
            .fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .background(Color(30, 30, 30, 1))
                .fillMaxWidth(0.9f)
        ) {
            Button(
                onClick = { },
                Modifier
                    .width(40.dp)
                    .height(40.dp)
                    .background(
                        color = Color(0xFF393939),
                        shape = RoundedCornerShape(size = 12.dp)
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = Color(57, 57, 57, 1))
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.fillMaxWidth(0.3f))

            Text(
                text = image.alt,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF333333),
                    textAlign = TextAlign.Center,
                )
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(560.dp)
                .clip(RoundedCornerShape(15.dp))
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(image.src.original)
                    .placeholder(R.drawable.placeholder_dark)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,

                contentDescription = image.alt,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clipToBounds()
                    .align(Alignment.Center)
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(26.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            bottomButton(image, context)
            Spacer(modifier = Modifier.width(60.dp))
            Button(onClick = {
                if (isFavorite) {
                    viewModel.deleteFavoriteImage(image)
                } else {
                    viewModel.addFavoriteImage(image)
                }

                isFavorite = !isFavorite
            }) {
                if (isFavorite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun bottomButton(
    image: ImageDetailResponse,
    context: Context
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(57, 57, 57, 1),
                contentColor = Color.Transparent
            ),
            modifier = Modifier
                .width(180.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .clickable { downloadImage(context, image.src.original) }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.img_2),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(48.dp)
                )
                Text(
                    text = "Download",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }
}


fun downloadImage(context: Context, imageUrl: String) {
    val downloader = ImageDownloader(context)
    downloader.downloadImage(imageUrl)
}


@Composable
fun PhotoDetailsScreen(
    imageId: Int,

    ) {
    val viewModel = hiltViewModel<CardScreenViewModel>()
    viewModel.getImageDetails(imageId)

    val state = viewModel.photoDetailsStateFlow.collectAsState()
    when (state.value) {
        is Resource.Loading<*> -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    modifier = Modifier.size(72.dp)
                )
                Text(text = "Please wait...")
            }
        }

        is Resource.Error<*> -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .size(64.dp),
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = "Warning icon"
                )

                Text(text = (state.value as Resource.Error<ImageDetailResponse>).errorMessage)
                Button(onClick = {
                    viewModel.getImageDetails(imageId)
                }) {
                    Text(text = "Retry")
                }
            }
        }

        is Resource.Success<*> -> {
            val image = (state.value as Resource.Success<ImageDetailResponse>).data
            CardInformation(image = image)
        }
    }
}