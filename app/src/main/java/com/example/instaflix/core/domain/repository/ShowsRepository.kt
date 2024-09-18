package com.example.instaflix.core.domain.repository

import com.example.instaflix.core.domain.models.Show
import com.example.instaflix.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface ShowsRepository {

    suspend fun getShows(
        showType: String,
        category: String,
        page: Int,
        fromRemote: Boolean
    ): Flow<Resource<List<Show>>>

    suspend fun insertShow(show: Show)

    suspend fun getShowById(id: Int): Flow<Resource<Show>>
}
