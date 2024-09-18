package com.example.instaflix.detail.presentation

import com.example.instaflix.core.domain.models.Show

data class ShowDetailState(

    val isLoading: Boolean = false,

    val show: Show? = null,

    val error: String? = null
)
