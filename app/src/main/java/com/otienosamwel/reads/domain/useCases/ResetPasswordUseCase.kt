package com.otienosamwel.reads.domain.useCases

import com.otienosamwel.reads.data.repository.auth.AuthRepository
import com.otienosamwel.reads.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(email: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        kotlin.runCatching { authRepository.passwordReset(email) }
            .onSuccess {
                emit(Resource.Success("A password reset link has been sent to your email"))
            }
            .onFailure {
                emit(Resource.Error("The password reset request could not be sent"))
            }
    }
}