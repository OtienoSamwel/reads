package com.otienosamwel.reads.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.otienosamwel.reads.data.model.ServerBook
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDao {

    @Insert
    suspend fun addBook(book: ServerBook)

    @Delete
    suspend fun removeBook(book: ServerBook)

    @Query("select * from server_book order by id desc limit 1 ")
    fun getLatestBook(): Flow<ServerBook>
}