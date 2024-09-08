package com.example.instaflix.home.data.repository

import com.example.instaflix.home.data.local.ShowsDatabase
import com.example.instaflix.home.data.mappers.toShow
import com.example.instaflix.home.data.mappers.toShowEntity
import com.example.instaflix.home.data.remote.api.ShowsApi
import com.example.instaflix.home.domain.models.Show
import com.example.instaflix.home.domain.repository.ShowsRepository
import com.example.instaflix.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowsRepositoryImpl @Inject constructor(
    private val api: ShowsApi,
    showsDatabase: ShowsDatabase
) : ShowsRepository {

    val showDao = showsDatabase.showsDao

    override suspend fun getShows(
        showType: String,
        category: String,
        apiKey: String,
        page: Int,
        fromRemote: Boolean
    ): Flow<Resource<List<Show>>> {
        return flow {

            emit(Resource.Loading(true))

            val localShows = showDao.getShowsByCategoryAndType(category, showType)

            if (localShows.isNotEmpty() && !fromRemote) {

                emit(Resource.Sucess(
                    data = localShows.map { it.toShow() }
                ))

                emit(Resource.Loading(false))
                return@flow
            }

            val showList = try {
                api.getShows(showType, category, apiKey, page).results
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data IO Exception"))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data Http Exception"))
                emit(Resource.Loading(false))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
                return@flow
            }

            showList.let { shows ->

                val showEntities = shows.map {
                    it.toShowEntity(
                        showType = showType,
                        category = category
                    )
                }

                showDao.insertListOfShows(showEntities)

                val showList = shows.map { it.toShow(showType, category) }

                emit(Resource.Sucess(data = showList))
                emit(Resource.Loading(false))
            }

        }
    }

    override suspend fun insertShow(show: Show) {
        val showEntity = show.toShowEntity()
        showDao.insertShow(showEntity)
    }

    override suspend fun getShowById(
        id: Int,
        showType: String,
        category: String
    ): Flow<Resource<Show>> {
        return flow {

            emit(Resource.Loading(true))

            val localShow = showDao.getShowById(id)

            emit(Resource.Sucess(data = localShow.toShow()))
            emit(Resource.Loading(false))

            return@flow
        }

    }
}
