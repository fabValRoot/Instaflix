package com.example.instaflix.detail.domain.use_cases

import com.example.instaflix.core.domain.models.Show
import com.example.instaflix.core.domain.repository.ShowsRepository
import com.example.instaflix.core.util.Resource
import kotlinx.coroutines.flow.Flow

class GetShowDetails(
    private val repository: ShowsRepository
) {

    suspend operator fun invoke(id: Int): Flow<Resource<Show>> {
        return repository.getShowById(id)
    }


}
