package com.example.instaflix.home.use_cases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.instaflix.core.data.repository.FakeShowsRepository
import com.example.instaflix.home.domain.use_cases.GetShows
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetShowsTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeShowsRepository: FakeShowsRepository
    private lateinit var getShows: GetShows

    @Before
    fun setUp() {
        fakeShowsRepository = FakeShowsRepository()
        getShows = GetShows(fakeShowsRepository)

    }

    @Test
    fun getShows_showsListNotEmpty() = runTest {
        fakeShowsRepository.setShows(isEmpty = false)
        val shows = getShows.invoke("String", "String", 1, false)

        shows.collect { result ->
            result.data?.let { assert(it.isNotEmpty()) }
        }

    }

    @Test
    fun getShows_showsListIsEmpty() = runTest {
        fakeShowsRepository.setShows(isEmpty = true)
        val shows = getShows.invoke("String", "String", 1, false)

        shows.collect { result ->
            result.data?.let { assert(it.isEmpty()) }
        }

    }

    @Test
    fun getShows_showsListCorrectSize() = runTest {
        fakeShowsRepository.setShows(isEmpty = false)
        val shows = getShows.invoke("String", "String", 1, false)

        shows.collect { result ->
            result.data?.let { assert(it.size == 3) }
        }

    }

    @Test
    fun getShowsWithType_showsListEmpty() = runTest {
        fakeShowsRepository.setShows(isEmpty = false)
        val shows = getShows.invoke("other", "String", 1, false)

        shows.collect { result ->
            result.data?.let { assert(it.isEmpty()) }
        }

    }

    @Test
    fun getShowsWithCategory_showsListEmpty() = runTest {
        fakeShowsRepository.setShows(isEmpty = false)
        val shows = getShows.invoke("String", "other", 1, false)

        shows.collect { result ->
            result.data?.let { assert(it.isEmpty()) }
        }

    }

}
