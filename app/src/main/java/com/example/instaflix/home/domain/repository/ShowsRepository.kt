package com.example.instaflix.home.domain.repository

import com.example.instaflix.home.domain.models.Show
import com.example.instaflix.util.Resource
import kotlinx.coroutines.flow.Flow

interface ShowsRepository {

    suspend fun getShows(
        showType: String,
        category: String,
        apiKey: String,
        page: Int,
        fromRemote: Boolean
    ): Flow<Resource<List<Show>>>

    suspend fun insertShow(show: Show)

    suspend fun getShowById(id: Int, showType: String, category: String): Show
}
