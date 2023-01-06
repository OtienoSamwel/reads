package com.otienosamwel.reads.domain.useCases

import com.otienosamwel.reads.data.model.Author
import com.otienosamwel.reads.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAuthorOfTheWeekUseCase @Inject constructor(
) {

    operator fun invoke(): Flow<Resource<Author>> = flow {
        emit(Resource.Loading<Author>())

        //val author: Author = bookRepository.getBookOfTheWeek()
    }

    companion object {
        private val TAG = GetAuthorOfTheWeekUseCase::class.simpleName
    }
}