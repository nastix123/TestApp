package by.eapp.testapp.presentation.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import by.eapp.testapp.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {

            val scale = remember {
                androidx.compose.animation.core.Animatable(0f)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(187, 16, 32, 1)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = null
                )
            }
            LaunchedEffect(key1 = true) {
                scale.animateTo(
                    targetValue = 0.3f,
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = {
                            OvershootInterpolator(2f).getInterpolation(it)
                        }
                    )
                )
                delay(3000L)
               navController.navigate(route = "home")
            }

        }
    )
}

