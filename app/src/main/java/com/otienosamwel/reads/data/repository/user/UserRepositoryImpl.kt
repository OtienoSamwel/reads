package com.otienosamwel.reads.data.repository.user

import com.otienosamwel.reads.data.local.UserDao
import com.otienosamwel.reads.data.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    override suspend fun clearUserData() {
        userDao.clearUserData()
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUserInfo(user)
    }
}
