package com.example.instaflix.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp


@Composable
fun HomeScreen(
    navController: NavController,
    state: HomeState,
) {

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .nestedScroll(nestedScrollConnection),
        contentAlignment = Alignment.BottomCenter
    ) {

        Column(
            modifier = Modifier
                .padding(top = 10.dp)
        ) {


            ShowListComposable(
                showType = "movie",
                category = "popular",
                state = state,
                navController = navController)

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            ShowListComposable(
                showType = "movie",
                category = "top_rated",
                state = state,
                navController = navController)

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            ShowListComposable(
                showType = "tv",
                category = "on_the_air",
                state = state,
                navController = navController)

            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            ShowListComposable(
                showType = "tv",
                category = "airing_today",
                state = state,
                navController = navController)

            Spacer(modifier = Modifier.padding(vertical = 8.dp))
        }

    }


}
