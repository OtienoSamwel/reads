package com.otienosamwel.reads.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.otienosamwel.reads.data.model.SearchItem


@Database(entities = [SearchItem::class], version = 1, exportSchema = false)
abstract class ReadsDatabase : RoomDatabase() {
    abstract fun getSearchDao(): SearchDao
}