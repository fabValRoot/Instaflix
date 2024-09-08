package com.example.instaflix.home.presentation

import com.example.instaflix.home.domain.models.Show

data class HomeState(

    val isLoading: Boolean = false,
    val error: String? = null,

    val popularMovies: List<Show> = emptyList(),
    val onAirTvShows: List<Show> = emptyList(),
    val topRatedMovies: List<Show> = emptyList(),
    val airTodayTvShows: List<Show> = emptyList(),
)
