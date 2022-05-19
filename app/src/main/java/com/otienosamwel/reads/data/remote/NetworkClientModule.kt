package com.otienosamwel.reads.data.remote

import android.content.Context
import com.otienosamwel.reads.utils.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkClientModule {

    @Provides
    @Singleton
    fun provideNetworkService(
        preferences: Preferences,
        @ApplicationContext context: Context
    ): NetworkService {
        return NetworkService(preferences = preferences, context = context)
    }
}