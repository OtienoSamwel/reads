package com.otienosamwel.reads.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.otienosamwel.reads.data.model.SearchItem
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {

    @Insert
    suspend fun insertSearchItem(item: SearchItem)

    @Query("select * from search_item where search_item.id = :id")
    fun getSearchItemById(id: Int): Flow<SearchItem>

    @Delete
    suspend fun deleteSearchItem(searchItem: SearchItem)

    @Query("delete from search_item")
    suspend fun deleteAllSearchItems()

    @Query("select * from search_item order by time desc limit 4")
    fun getAllSearchItems(): Flow<List<SearchItem>>
}