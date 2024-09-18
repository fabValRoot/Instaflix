package com.example.instaflix.core.data.remote.api

import com.example.instaflix.core.data.remote.dto.ApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowsApi {

    @GET("{show_type}/{category}")
    suspend fun getShows(
        @Path("show_type") showType: String,
        @Path("category") category: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int
    ): ApiResponseDto


    companion object {
        const val API_KEY = "645197735faaceb67ab59d10899455a6"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }
}
