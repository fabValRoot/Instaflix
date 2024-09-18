package com.example.instaflix.home.domain.use_cases

import com.example.instaflix.core.domain.models.Show
import com.example.instaflix.core.domain.repository.ShowsRepository
import com.example.instaflix.core.util.Resource
import kotlinx.coroutines.flow.Flow

class GetShows(
    private val repository: ShowsRepository
) {

    suspend operator fun invoke(
        showType: String,
        category: String,
        page: Int,
        fromRemote: Boolean
    ): Flow<Resource<List<Show>>> {
        return repository.getShows(
            showType = showType,
            category = category,
            page = page,
            fromRemote = fromRemote
        )
    }


}
