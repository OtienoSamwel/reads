package com.example.reads.data.local

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
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): ReadsDatabase {
        return Room.databaseBuilder(context, ReadsDatabase::class.java, "reads_db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(database: ReadsDatabase): ReadsDao {
        return database.readsDao()
    }
}