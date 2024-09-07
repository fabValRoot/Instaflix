package com.example.instaflix.detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.instaflix.home.domain.models.Show
import com.example.instaflix.util.ShowPosterComposable


@Composable
fun ShowDetailScreen(
    navController: NavController,
    show: Show,
    detailState: ShowDetailState
) {

    val imgUrl = "https://image.tmdb.org/t/p/w500/${show.largeImgPath}"

    val imgPainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imgUrl)
            .size(coil.size.Size.ORIGINAL)
            .build()
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            ShowPosterComposable(show = show)

            Text(
                text = show.showType,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = show.overview,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.width(8.dp))


        }

    }


}
