package com.example.instaflix.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.instaflix.R
import com.example.instaflix.detail.presentation.ShowDetailScreen
import com.example.instaflix.detail.presentation.ShowDetailsViewModel
import com.example.instaflix.home.presentation.HomeScreen
import com.example.instaflix.home.presentation.HomeState
import com.example.instaflix.home.presentation.HomeViewModel
import com.example.instaflix.core.theme.InstaflixTheme
import com.example.instaflix.core.util.ErrorComposable
import com.example.instaflix.core.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InstaflixTheme {

                val viewModel: HomeViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()

                HomeNav(
                    state = state,
                )
            }
        }
    }
}


@Composable
fun HomeNav(
    state: HomeState,
) {
    val navController = rememberNavController()

    val detailsViewModel :ShowDetailsViewModel = hiltViewModel()
    val detailState by detailsViewModel.showDetailState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME_SCREEN
    ) {

        composable(
            Routes.HOME_SCREEN,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
        ) {
            HomeScreen(navController = navController, state = state)
        }

        composable(
            "${Routes.SHOW_DETAIL_SCREEN}?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
        ) {

            val id = it.arguments?.getInt("id") ?: 0

            LaunchedEffect(key1 = true) {
                detailsViewModel.fetchShowDetails(id)
            }

            if (detailState.show != null) {
                ShowDetailScreen(
                    show = detailState.show!!,
                )
            } else {
                ErrorComposable(message = stringResource(R.string.show_not_found_retry))
            }

        }

    }

}

