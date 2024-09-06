package com.example.instaflix.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaflix.home.data.remote.api.ShowsApi.Companion.API_KEY
import com.example.instaflix.home.domain.repository.ShowsRepository
import com.example.instaflix.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val showRepository: ShowsRepository
): ViewModel() {

    init {
        viewModelScope.launch {

            showRepository.getShows(
                showType = "movie",
                category = "popular",
                page = 1,
                apiKey = API_KEY,
                fromRemote = true,
            ).collect { result ->
                when(result){
                    is Resource.Error -> println("ERROR: ${result.message}")
                    is Resource.Loading -> println("LOADING")
                    is Resource.Sucess -> println("SUCESS: ${result.data}")
                }

            }

        }
    }


}
