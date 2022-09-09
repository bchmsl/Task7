package com.example.task7.di

import com.example.task7.domain.repositories.MainRepository
import com.example.task7.network.ApiClient
import com.example.task7.ui.main.MainRepositoryImpl
import com.example.task7.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiClient =
        retrofit.create(ApiClient::class.java)

    @Provides
    @Singleton
    fun provideMainRepositoryImpl(api: ApiClient): MainRepository=
        MainRepositoryImpl(api)
}