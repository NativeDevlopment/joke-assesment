package com.assginment.domain.usecases

import com.assginment.domain.common.ResultState
import com.assginment.domain.entity.JokeEntity
import com.assginment.domain.repository.LocalJokeListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetLocalJokeUseCaseImpl  (val repository: LocalJokeListRepository) : GetLocalJokeUseCase {
    override  fun excute(): Flow<ResultState<List<JokeEntity>>> = flow<ResultState<List<JokeEntity>>> {
        repository.getLocalJoke().collect{emit(it)}
    }

        . catch { e ->
            emit(ResultState.error("Couldn't understand servers response. Please try later.", null))
        }.flowOn(Dispatchers.IO)
}