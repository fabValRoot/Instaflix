package com.example.instaflix.core.di

import com.example.instaflix.core.data.repository.ShowsRepositoryImpl
import com.example.instaflix.core.domain.repository.ShowsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindShowsRepo(
        showsRepositoryImpl: ShowsRepositoryImpl
    ): ShowsRepository

}
