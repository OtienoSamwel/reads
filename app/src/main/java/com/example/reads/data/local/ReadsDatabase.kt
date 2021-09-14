package com.example.reads.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.reads.data.model.Book

@Database(entities = [Book::class], version = 1)
abstract class ReadsDatabase : RoomDatabase() {
    abstract fun readsDao(): ReadsDao
}