package com.example.instaflix.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.instaflix.home.data.remote.api.ShowsApi
import com.example.instaflix.home.domain.models.Show


@Composable
fun HomeScreenItem(
    show: Show,
    navController: NavController,
    modifier: Modifier = Modifier
) {


    val imgUrl = "${ShowsApi.IMAGE_URL}${show.largeImgPath}"

    val imgPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imgUrl)
            .size(coil.size.Size.ORIGINAL)
            .build()
    )

    val imgState = imgPainter.state

    Box(
        modifier = modifier
            .clickable {
                navController.navigate("${Routes.SHOW_DETAIL_SCREEN}?id=${show.id}")
            }
    ) {
        if (imgState is AsyncImagePainter.State.Success) {

            val imgBitmap = imgState.result.drawable.toBitmap()

            Image(
                bitmap = imgBitmap.asImageBitmap(),
                contentDescription = "${show.title}${show.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        if (imgState is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.Center)
                    .scale(0.5f)
            )
        }

        if (imgState is AsyncImagePainter.State.Error) {
            Icon(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center),
                imageVector = Icons.Rounded.Warning,
                contentDescription = "Error loading image"
            )
        }
    }

}
