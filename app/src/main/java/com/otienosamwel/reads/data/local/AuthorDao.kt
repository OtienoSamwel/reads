package com.otienosamwel.reads.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.otienosamwel.reads.data.model.Author
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthorDao {

    @Insert
    suspend fun addAuthor(author: Author)

    @Delete
    suspend fun removeAuthor(author: Author)

    @Query("select * from author order by id desc limit 1")
    fun getLatestAuthorEntry(): Flow<Author>
}