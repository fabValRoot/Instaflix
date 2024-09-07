package com.example.instaflix.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.instaflix.util.ShowItem


@Composable
fun ShowListComposable(
    showType: String,
    category: String,
    state: HomeState,
    navController: NavController,
) {

    val showsList = when (showType) {
        "movie" -> {
            when (category) {
                "popular" -> state.popularMovies.take(10)
                "top_rated" -> state.topRatedMovies.take(10)
                else -> {state.popularMovies.take(10)}
            }
        }

        "tv"-> {
            when (category) {
                "airing_today" -> state.airTodayTvShows.take(10)
                "on_the_air" -> state.onAirTvShows.take(10)
                else -> {state.popularMovies.take(10)}
            }
        }

        else -> {
            state.onAirTvShows.take(10)
        }
    }

    Column {
        Row (
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "$showType  $category",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp
            )
        }
    }

    LazyRow {
        items(showsList.size) {

            var paddingEnd = 0.dp
            if (it == showsList.size - 1) {
                paddingEnd = 16.dp
            }

            ShowItem(
                show = showsList[it],
                navController = navController,
                modifier = Modifier
                    .padding(end = paddingEnd)
                    .width(100.dp)
                    .height(150.dp)
            )
        }
    }

}
