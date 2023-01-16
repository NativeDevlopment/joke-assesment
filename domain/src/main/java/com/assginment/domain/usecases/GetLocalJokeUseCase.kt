package com.assginment.domain.usecases

import com.assginment.domain.common.ResultState
import com.assginment.domain.entity.JokeEntity
import kotlinx.coroutines.flow.Flow

interface GetLocalJokeUseCase {
    fun  excute (): Flow<ResultState<List<JokeEntity>>>

}