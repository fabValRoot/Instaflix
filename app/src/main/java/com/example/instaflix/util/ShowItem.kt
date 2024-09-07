package com.example.instaflix.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.instaflix.home.domain.models.Show


@Composable
fun ShowItem(
    show: Show,
    navController: NavController,
    modifier: Modifier = Modifier
) {


    val imgUrl = "https://image.tmdb.org/t/p/w500/${show.smallImgPath}"

    val imgPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imgUrl)
            .size(coil.size.Size.ORIGINAL)
            .build()
    )

    val imgState = imgPainter.state

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                //todo: navigate to show detail screen
            }
    ) {
        if (imgState is AsyncImagePainter.State.Success){

            val imgBitmap = imgState.result.drawable.toBitmap()

            Image(
                bitmap = imgBitmap.asImageBitmap(),
                //todo: add name when tv show
                contentDescription = show.title,
                modifier = Modifier.fillMaxSize()
            )
        }

        if (imgState is AsyncImagePainter.State.Loading){
            //todo: add loading indicator
        }

        if (imgState is AsyncImagePainter.State.Error){
            //todo: add error indicator
        }
    }

}
