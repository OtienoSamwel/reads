package com.otienosamwel.reads.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.otienosamwel.reads.data.model.Author
import com.otienosamwel.reads.data.model.SearchItem
import com.otienosamwel.reads.data.model.ServerBook
import com.otienosamwel.reads.data.model.User


@Database(
    entities = [SearchItem::class, ServerBook::class, Author::class, User::class],
    version = 2,
    exportSchema = false

)
@TypeConverters(Converters::class)
abstract class ReadsDatabase : RoomDatabase() {
    abstract fun getSearchDao(): SearchDao

    abstract fun getBookDao(): BookDao

    abstract fun getAuthorDao(): AuthorDao

    abstract fun getUserDao () : UserDao
}