package com.example.reads.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.reads.data.model.Item

@TypeConverters(Converters::class)
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ReadsDatabase : RoomDatabase() {
    abstract fun readsDao(): ReadsDao
}