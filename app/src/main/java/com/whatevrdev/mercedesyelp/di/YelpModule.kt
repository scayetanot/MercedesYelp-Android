package com.whatevrdev.mercedesyelp.di

import com.whatevrdev.data.network.ApiHelper
import com.whatevrdev.data.network.ApiHelperImpl
import com.whatevrdev.data.network.ApiService
import com.whatevrdev.data.repositories.YelpApiRepositoryImpl
import com.whatevrdev.domain.interfaces.YelpApiRepository
import com.whatevrdev.domain.usecases.YelpUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class YelpModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideApiHelper(
        apiService: ApiService
    ): ApiHelper {
        return ApiHelperImpl(apiService)
    }

    @Provides
    fun provideYelpApiRepository(
        yelpApi: ApiHelper
    ): YelpApiRepository {
        return YelpApiRepositoryImpl(yelpApi)
    }

    @Provides
    fun provideYelpUseCases(
        yelpApiRepository: YelpApiRepository
    ): YelpUseCases {
        return YelpUseCases(yelpApiRepository)
    }

}