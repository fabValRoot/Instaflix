package com.example.instaflix.home.data.repository

import com.example.instaflix.home.data.remote.api.ShowsApi
import com.example.instaflix.home.domain.models.Show
import com.example.instaflix.home.domain.repository.ShowsRepository
import com.example.instaflix.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowsRepositoryImpl @Inject constructor(
    private val api: ShowsApi
) : ShowsRepository {

    //TODO: implement methods from the DB when that is added to the repo


    override suspend fun getShows(
        showType: String,
        category: String,
        apiKey: String,
        page: Int,
        fromRemote: Boolean
    ): Flow<Resource<List<Show>>> {
        return flow {

            emit(Resource.Loading(true))

            val showList = try {
                api.getShows(showType, category, apiKey, page)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            showList.let { shows ->
                println("GET SHOWS called ")
                if (showList != null) {
                    println(showList.results)
                }
                emit(Resource.Loading(false))
            }

        }
    }
}
