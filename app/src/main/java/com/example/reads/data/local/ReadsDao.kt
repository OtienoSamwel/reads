package com.example.reads.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.reads.data.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadsDao {
    @Query("SELECT * FROM Item")
    fun getDbBooks(): Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(items: List<Item>)
}