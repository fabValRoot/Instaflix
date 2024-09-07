package com.example.instaflix.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instaflix.home.domain.repository.ShowsRepository
import com.example.instaflix.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    private val showRepository: ShowsRepository

) : ViewModel() {

    private val _showDetailState = MutableStateFlow(ShowDetailState())
    val showDetailState = _showDetailState.asStateFlow()


    fun fetchShowDetails(id : Int) {
        viewModelScope.launch {

            showRepository.getShowById(id,"","")
                .collect { result ->
                    when (result) {
                        is Resource.Error -> TODO()
                        is Resource.Loading -> {
                            _showDetailState.value = showDetailState.value.copy(
                                isLoading = result.isLoading
                            )
                        }
                        is Resource.Sucess -> {
                            _showDetailState.value = showDetailState.value.copy(
                                show = result.data
                            )
                        }
                    }

                }


        }
    }

}
