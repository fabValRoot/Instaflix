package com.example.instaflix.home.presentation

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.instaflix.R
import com.example.instaflix.core.util.ShowItem
import com.example.instaflix.home.presentation.HomeViewModel.Companion.AIRING_TODAY_CATEGORY
import com.example.instaflix.home.presentation.HomeViewModel.Companion.MOVIE_TYPE
import com.example.instaflix.home.presentation.HomeViewModel.Companion.ON_THE_AIR_CATEGORY
import com.example.instaflix.home.presentation.HomeViewModel.Companion.POPULAR_CATEGORY
import com.example.instaflix.home.presentation.HomeViewModel.Companion.TOP_RATED_CATEGORY
import com.example.instaflix.home.presentation.HomeViewModel.Companion.TV_TYPE


@Composable
fun ShowListComposable(
    showType: String,
    category: String,
    state: HomeState,
    navController: NavController,
) {

    val showsList = when (showType) {
        MOVIE_TYPE -> {
            when (category) {
                POPULAR_CATEGORY -> state.popularMovies.take(10)
                TOP_RATED_CATEGORY -> state.topRatedMovies.take(10)
                else -> {
                    state.popularMovies.take(10)
                }
            }
        }

        TV_TYPE -> {
            when (category) {
                AIRING_TODAY_CATEGORY -> state.airTodayTvShows.take(10)
                ON_THE_AIR_CATEGORY -> state.onAirTvShows.take(10)
                else -> {
                    state.popularMovies.take(10)
                }
            }
        }

        else -> {
            state.onAirTvShows.take(10)
        }
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${showType.uppercase()}/${
                    category.split("_").joinToString(" ").uppercase()
                }",
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
