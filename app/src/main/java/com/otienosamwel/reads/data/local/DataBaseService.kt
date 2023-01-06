package com.otienosamwel.reads.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ReadsDatabaseModule {

    @Provides
    @Singleton
    fun provideReadsDatabase(@ApplicationContext context: Context): ReadsDatabase {
        return Room.databaseBuilder(context, ReadsDatabase::class.java, "reads_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchDao(readsDatabase: ReadsDatabase): SearchDao {
        return readsDatabase.getSearchDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(readsDatabase: ReadsDatabase): UserDao {
        return readsDatabase.getUserDao()
    }
}