package com.example.instaflix.core.di

import com.example.instaflix.detail.domain.use_cases.GetShowDetails
import com.example.instaflix.core.domain.repository.ShowsRepository
import com.example.instaflix.home.domain.use_cases.GetShows
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    @Singleton
    fun providesDetailsUseCase(repository: ShowsRepository): GetShowDetails {
        return GetShowDetails(repository)
    }

    @Provides
    @Singleton
    fun providesSHowsUseCase(repository: ShowsRepository): GetShows {
        return GetShows(repository)
    }
}


