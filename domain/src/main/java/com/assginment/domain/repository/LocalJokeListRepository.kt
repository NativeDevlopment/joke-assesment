package com.assginment.domain.repository

import com.assginment.domain.common.ResultState
import com.assginment.domain.entity.JokeEntity
import kotlinx.coroutines.flow.Flow

interface LocalJokeListRepository {
    fun getLocalJoke(): Flow<ResultState<List<JokeEntity>>>

}