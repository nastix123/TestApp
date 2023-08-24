package by.eapp.testapp.presentation.ui.homescreen

import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import by.eapp.testapp.R
import by.eapp.testapp.presentation.ui.navigation.BottomNavigationItem
import com.android.volley.toolbox.ImageRequest
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

data class GridItem (

    val height: Dp
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun RandomColorBox(
    item: GridItem,
    photo: by.eapp.testapp.data.db.database.imageList.Image,
    navController:NavController
) {
    androidx.compose.material3.Card(
    modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .height(item.height),
    shape = RoundedCornerShape(12.dp),
    onClick = {
        navController.navigate(BottomNavigationItem.Details.wihArgs(photo.id))
    }
) {
    Box {
        GlideImage(
            model = photo.src.large2x,
            contentDescription = photo.alt,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillBounds,
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = photo.photographer,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                )
            )
        }
    }
}

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(item.height)
        .clip(RoundedCornerShape(10.dp))
       )
}

@Preview(showBackground = true)
@Composable
fun PreviewCard(){

}
