package com.example.instaflix.home.data.remote.dto

data class ApiResponseDto(
    val page: Int,
    val total_pages: Int,
    val total_results: Int,
    val results: List<ShowDTo>
)
