package com.example.reads.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.reads.data.model.Book
import com.example.reads.data.model.Item

@Dao
interface ReadsDao {
    @Query("SELECT * FROM Item")
    fun getDbBooks(): List<Item>

    @Insert(entity = Item::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(items: List<Item>)
}