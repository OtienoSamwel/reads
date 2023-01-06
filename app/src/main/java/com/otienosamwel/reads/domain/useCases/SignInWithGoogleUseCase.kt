package com.otienosamwel.reads.domain.useCases

import com.otienosamwel.reads.data.remote.SignUpResponse
import com.otienosamwel.reads.data.repository.auth.AuthRepository
import com.otienosamwel.reads.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(tokenID: String): Flow<Resource<SignUpResponse>> = flow {
        emit(Resource.Loading())
        kotlin.runCatching { authRepository.signInUserWithGoogle(tokenID) }
            .onSuccess {
                emit(Resource.Success(it))
            }
            .onFailure {
                emit(Resource.Error(it.message.toString()))
            }
    }
}