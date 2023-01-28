package com.whatevrdev.mercedesyelp.di

import android.content.Context
import com.whatevrdev.mercedesyelp.utils.LocationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class LocationModule {
    @Provides
    fun provideLocationProvider(
        @ApplicationContext appContext: Context,
    ): LocationProvider {
        return LocationProvider(appContext)
    }

}