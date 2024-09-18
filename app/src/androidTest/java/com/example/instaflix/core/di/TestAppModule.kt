package com.example.instaflix.core.di

import android.app.Application
import androidx.room.Room
import com.example.instaflix.core.data.local.ShowsDatabase
import com.example.instaflix.core.data.remote.api.ShowsApi
import com.example.instaflix.core.data.remote.api.ShowsApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class TestAppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideShowsApi(): ShowsApi {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(ShowsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideShowsDatabase(app: Application): ShowsDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            ShowsDatabase::class.java,
        ).build()
    }

}
