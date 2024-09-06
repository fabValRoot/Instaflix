package com.example.instaflix.home.domain.repository

import com.example.instaflix.home.domain.models.Show
import com.example.instaflix.util.Resource
import kotlinx.coroutines.flow.Flow

interface ShowsRepository {

    //TODO: this should also include local DB funs it will be added when working on the local DB.

    suspend fun getShows(
        showType: String,
        category: String,
        apiKey: String,
        page: Int,
        fromRemote: Boolean
    ): Flow<Resource<List<Show>>>
}
