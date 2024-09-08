package com.example.instaflix.home.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.instaflix.di.AppModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


@HiltAndroidTest
@UninstallModules(AppModule::class)
@RunWith(AndroidJUnit4::class)
class ShowDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var showsDatabase: ShowsDatabase
    private lateinit var showDao: ShowsDao

    @Before
    fun setup() {
        hiltRule.inject()
        showDao = showsDatabase.showsDao
    }

    @After
    fun tearDown() {
        showsDatabase.close()
    }

    @Test
    fun getAllShowsFromDB_showListEmpty() = runTest {
        //nothing added to DB so it should return empty list
        assert(showDao.getShowsByCategoryAndType("", "").isEmpty())
    }

    @Test
    fun addShowsToDB_getAllShowsFromDB_showListNotEmpty() = runTest {

        val showEntityList = listOf(
            ShowEntity(
                smallImgPath = "String",
                id = 1,
                language = "String",
                overview = "String",
                popularity = 0.0,
                largeImgPath = "String",
                releaseDate = "String",
                title = "String",
                voteAverage = 0.0,
                name = "String",
                category = "String",
                showType = "String"
            ),
            ShowEntity(
                smallImgPath = "String",
                id = 2,
                language = "String",
                overview = "String",
                popularity = 0.0,
                largeImgPath = "String",
                releaseDate = "String",
                title = "String",
                voteAverage = 0.0,
                name = "String",
                category = "String",
                showType = "String"
            )
        )

        showDao.insertListOfShows(showEntityList)
        assert(showDao.getShowsByCategoryAndType("String", "String").isNotEmpty())

    }

    @Test
    fun addShowToDB_getShowByIdFromDB_showNameEqual() = runTest {

        val showEntity =
            ShowEntity(
                smallImgPath = "String",
                id = 1,
                language = "String",
                overview = "String",
                popularity = 0.0,
                largeImgPath = "String",
                releaseDate = "String",
                title = "String",
                voteAverage = 0.0,
                name = "String",
                category = "String",
                showType = "String"
            )


        showDao.insertShow(showEntity)
        assert(showDao.getShowById(1).name == showEntity.name)

    }

}
