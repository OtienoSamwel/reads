package com.example.reads.data.remote.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Api {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(): Endpoints {
        return retrofit.create(Endpoints::class.java)
    }
}