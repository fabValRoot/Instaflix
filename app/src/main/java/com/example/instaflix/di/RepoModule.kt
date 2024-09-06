package com.example.instaflix.di

import com.example.instaflix.home.data.repository.ShowsRepositoryImpl
import com.example.instaflix.home.domain.repository.ShowsRepository
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
