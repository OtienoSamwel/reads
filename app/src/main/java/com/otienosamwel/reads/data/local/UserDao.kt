package com.otienosamwel.reads.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.otienosamwel.reads.data.model.User


@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUserInfo(user: User)

    @Query("delete from user")
    suspend fun clearUserData()
}