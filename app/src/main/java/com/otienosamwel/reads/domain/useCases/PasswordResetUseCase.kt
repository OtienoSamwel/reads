package com.otienosamwel.reads.domain.useCases

import com.otienosamwel.reads.data.remote.PasswordResetResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PasswordResetUseCase @Inject constructor() {
    operator fun invoke ( email :String) : Flow<PasswordResetResponse> = flow {

    }
}