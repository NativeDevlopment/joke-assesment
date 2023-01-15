package com.assginment.domain.usecases

import android.util.Log
import com.assginment.domain.common.ResultState
import com.assginment.domain.entity.JokeEntity
import com.assginment.domain.repository.CodingTaskRepository
import com.assginment.domain.usecases.GetJokeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetJokeUseCaseImpl (val repository: CodingTaskRepository) : GetJokeUseCase {
    override  fun excute(): Flow<ResultState<JokeEntity>> = flow<ResultState<JokeEntity>> {
        Log.e("flow>>>",">>>.")
        repository.getJoke().collect{emit(it)}
        }

        . catch { e ->
            emit(ResultState.error("Couldn't understand servers response. Please try later.", null))
        }.flowOn(Dispatchers.IO)
    }

