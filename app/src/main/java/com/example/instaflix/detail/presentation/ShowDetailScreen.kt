package com.example.instaflix.detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.instaflix.home.domain.models.Show
import com.example.instaflix.util.ShowPosterComposable


@Composable
fun ShowDetailScreen(
    show: Show,
) {

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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            )
            {
                Text(
                    text = "Title: ${show.title}${show.name}",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Show Type: ${show.showType}",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Category: ${show.category}",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Language: ${show.language}",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Release Date: ${show.releaseDate}",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Overview: ${show.overview}",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

        }

    }

}
