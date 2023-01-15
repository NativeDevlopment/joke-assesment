package com.assginment.domain.repository

import com.assginment.domain.common.ResultState
import com.assginment.domain.entity.JokeEntity
import kotlinx.coroutines.flow.Flow

interface CodingTaskRepository {
   fun getJoke(): Flow<ResultState<JokeEntity>>

}