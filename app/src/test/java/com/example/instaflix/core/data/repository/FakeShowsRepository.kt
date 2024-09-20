package com.example.instaflix.core.data.repository

import android.util.Log
import com.example.instaflix.core.domain.models.Show
import com.example.instaflix.core.domain.repository.ShowsRepository
import com.example.instaflix.core.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeShowsRepository : ShowsRepository {

    private var showsList = mutableListOf<Show>()

    fun setShows(isEmpty: Boolean) {
        if (isEmpty) {
            showsList.clear()
        } else {
            showsList = mutableListOf(
                Show(
                    smallImgPath = "String",
                    id = 1,
                    language = "String",
                    overview = "String",
                    popularity = 0.0,
                    largeImgPath = "String",
                    releaseDate = "String",
                    title = "String",
                    voteAverage = 0.0,
                    name = "1",
                    category = "String",
                    showType = "String"
                ),
                Show(
                    smallImgPath = "String",
                    id = 2,
                    language = "String",
                    overview = "String",
                    popularity = 0.0,
                    largeImgPath = "String",
                    releaseDate = "String",
                    title = "String",
                    voteAverage = 0.0,
                    name = "2",
                    category = "String",
                    showType = "String"
                ),
                Show(
                    smallImgPath = "String",
                    id = 3,
                    language = "String",
                    overview = "String",
                    popularity = 0.0,
                    largeImgPath = "String",
                    releaseDate = "String",
                    title = "String",
                    voteAverage = 0.0,
                    name = "3",
                    category = "String",
                    showType = "String"
                )
            )
        }
    }

    override suspend fun getShows(
        showType: String,
        category: String,
        page: Int,
        fromRemote: Boolean
    ): Flow<Resource<List<Show>>> {
        return flow {

            Resource.Sucess(data = showsList)
        }
    }

    override suspend fun insertShow(show: Show) {
        showsList.add(show)
    }

    override suspend fun getShowById(
        id: Int,
    ): Flow<Resource<Show>> {
        return flow {

            Resource.Sucess(data = showsList.find { it.id == id })
        }

    }
}
