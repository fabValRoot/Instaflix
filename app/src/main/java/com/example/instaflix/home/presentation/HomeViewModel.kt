package com.example.instaflix.home.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaflix.home.data.remote.api.ShowsApi.Companion.API_KEY
import com.example.instaflix.home.domain.repository.ShowsRepository
import com.example.instaflix.util.Resource
import com.example.instaflix.util.isInternetAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val showRepository: ShowsRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            fetchShows("movie", "popular")
            fetchShows("movie", "top_rated")
            fetchShows("tv", "airing_today")
            fetchShows("tv", "on_the_air")
        }
    }

    private suspend fun fetchShows(
        showType: String,
        category: String,
    ) {
        showRepository.getShows(
            showType = showType,
            category = category,
            page = 1,
            apiKey = API_KEY,
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
                            "popular" -> it.copy(popularMovies = result.data ?: emptyList())
                            "top_rated" -> it.copy(topRatedMovies = result.data ?: emptyList())
                            "airing_today" -> it.copy(airTodayTvShows = result.data ?: emptyList())
                            "on_the_air" -> it.copy(onAirTvShows = result.data ?: emptyList())

                            else -> {
                                it.copy()
                            }
                        }
                    }
                }
            }
        }
    }


}
