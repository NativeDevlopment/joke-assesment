package com.assginment.domain.usecases

import com.assginment.domain.common.ResultState
import com.assginment.domain.entity.JokeEntity
import kotlinx.coroutines.flow.Flow

interface GetJokeUseCase {
     fun  excute (): Flow<ResultState<JokeEntity>>
}