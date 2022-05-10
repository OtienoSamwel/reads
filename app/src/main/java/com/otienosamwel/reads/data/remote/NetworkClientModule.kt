package com.otienosamwel.reads.data.remote

import com.otienosamwel.reads.utils.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkClientModule {

    @Provides
    @Singleton
    fun provideNetworkService(preferences: Preferences): NetworkService {
        return NetworkService(preferences = preferences)
    }
}