package com.example.instaflix.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.instaflix.detail.presentation.ShowDetailScreen
import com.example.instaflix.detail.presentation.ShowDetailsViewModel
import com.example.instaflix.ui.theme.InstaflixTheme
import com.example.instaflix.util.ErrorComposable
import com.example.instaflix.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InstaflixTheme {

                val viewModel: HomeViewModel by viewModels()
                val state = viewModel.state.collectAsState().value

                val detailsViewModel: ShowDetailsViewModel by viewModels()

                HomeNav(
                    state = state,
                    detailsViewModel = detailsViewModel
                )
            }
        }
    }
}


@Composable
fun HomeNav(
    state: HomeState,
    detailsViewModel: ShowDetailsViewModel
) {
    val navController = rememberNavController()

    val detailState = detailsViewModel.showDetailState.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = Routes.HOME_SCREEN
    ) {

        composable(Routes.HOME_SCREEN) {
            HomeScreen(navController = navController, state = state)
        }

        composable(
            "${Routes.SHOW_DETAIL_SCREEN}?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {

            val id = it.arguments?.getInt("id") ?: 0

            LaunchedEffect(key1 = true) {
                detailsViewModel.fetchShowDetails(id)
            }

            if (detailState.show != null) {
                ShowDetailScreen(
                    show = detailState.show,
                )
            } else {
                ErrorComposable(message = "Show Not Found, Retry")
            }

        }

    }

}

