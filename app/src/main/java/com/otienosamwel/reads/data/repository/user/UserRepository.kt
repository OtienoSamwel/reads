package com.otienosamwel.reads.data.repository.user

import com.otienosamwel.reads.data.model.User

interface UserRepository {

    suspend fun addUser(user: User)

    suspend fun clearUserData()

    suspend fun updateUser(user: User)
}