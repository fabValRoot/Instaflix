package com.example.instaflix.home.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaflix.core.util.Resource
import com.example.instaflix.core.util.isInternetAvailable
import com.example.instaflix.home.domain.use_cases.GetShows
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val showsUseCase: GetShows,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            fetchShows(MOVIE_TYPE, POPULAR_CATEGORY,)
            fetchShows(MOVIE_TYPE, TOP_RATED_CATEGORY)
            fetchShows(TV_TYPE, AIRING_TODAY_CATEGORY)
            fetchShows(TV_TYPE, ON_THE_AIR_CATEGORY)
        }
    }

    suspend fun fetchShows(
        showType: String,
        category: String,
    ) {
        showsUseCase(
            showType = showType,
            category = category,
            page = 1,
            fromRemote = isInternetAvailable(context),
        ).collect { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update { it.copy(error = result.message) }
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = result.isLoading) }
                }

                is Resource.Sucess -> {
                    _state.update {
                        when (category) {
                            POPULAR_CATEGORY -> it.copy(
                                popularMovies = result.data ?: emptyList()
                            )

                            TOP_RATED_CATEGORY -> it.copy(
                                topRatedMovies = result.data ?: emptyList()
                            )

                            AIRING_TODAY_CATEGORY -> it.copy(
                                airTodayTvShows = result.data ?: emptyList()
                            )

                            ON_THE_AIR_CATEGORY-> it.copy(
                                onAirTvShows = result.data ?: emptyList()
                            )

                            else -> {
                                it.copy()
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val MOVIE_TYPE = "movie"
        const val TV_TYPE = "tv"
        const val POPULAR_CATEGORY = "popular"
        const val TOP_RATED_CATEGORY = "top_rated"
        const val AIRING_TODAY_CATEGORY = "airing_today"
        const val ON_THE_AIR_CATEGORY = "on_the_air"
    }


}
