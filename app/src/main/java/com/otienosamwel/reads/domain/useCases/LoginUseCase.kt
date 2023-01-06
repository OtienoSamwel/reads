package com.otienosamwel.reads.domain.useCases

import com.otienosamwel.reads.data.remote.SignInResponse
import com.otienosamwel.reads.data.repository.auth.AuthRepository
import com.otienosamwel.reads.data.repository.user.UserRepository
import com.otienosamwel.reads.domain.model.Resource
import com.otienosamwel.reads.ui.presentation.features.auth.AuthViewModel
import com.otienosamwel.reads.utils.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val preferences: Preferences
) {
    operator fun invoke(email: String, password: String): Flow<Resource<SignInResponse>> = flow {

        emit(Resource.Loading())

        kotlin.runCatching {
            authRepository.signInUserWithEmail(data = AuthViewModel.LoginData(email, password))
        }.onSuccess {

            userRepository.addUser(it.user)
            preferences.setToken(it.accessToken)
            preferences.setRefreshToken(it.refreshToken)

            emit(Resource.Success(it))
        }.onFailure {
            emit(Resource.Error(message = it.message.toString()))
        }
    }
}