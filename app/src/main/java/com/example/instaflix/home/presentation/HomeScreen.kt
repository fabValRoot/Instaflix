package com.example.instaflix.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.instaflix.util.ErrorComposable
import com.example.instaflix.util.HomeScreenItem

@Composable
fun HomeScreen(
    navController: NavController,
    state: HomeState,
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            ) {

                if (state.error != null) {
                    ErrorComposable(
                        message = state.error,
                    )
                }

                if (state.popularMovies.isEmpty() || state.isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                            .scale(0.5f)
                    )
                } else {
                    val show = state.popularMovies.random()

                    HomeScreenItem(
                        show = show,
                        navController = navController,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.5f),
                                    )
                                )
                            )
                    )

                    Text(
                        text = show.title,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .paddingFromBaseline(bottom = 20.dp)
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                ShowListComposable(
                    showType = "movie",
                    category = "popular",
                    state = state,
                    navController = navController
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                ShowListComposable(
                    showType = "movie",
                    category = "top_rated",
                    state = state,
                    navController = navController
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                ShowListComposable(
                    showType = "tv",
                    category = "on_the_air",
                    state = state,
                    navController = navController
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                ShowListComposable(
                    showType = "tv",
                    category = "airing_today",
                    state = state,
                    navController = navController
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}
