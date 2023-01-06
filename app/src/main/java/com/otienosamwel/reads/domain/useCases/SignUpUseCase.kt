package com.otienosamwel.reads.domain.useCases

import com.otienosamwel.reads.data.remote.SignUpResponse
import com.otienosamwel.reads.data.repository.auth.AuthRepository
import com.otienosamwel.reads.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<Resource<SignUpResponse>> = flow {

        emit(Resource.Loading())

        kotlin.runCatching {
            val data = authRepository.signUpUserWithEmail(
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password
            )

            emit(Resource.Success(data))
        }.onFailure {
            emit(Resource.Error(it.message.toString(), null))
        }
    }
}
