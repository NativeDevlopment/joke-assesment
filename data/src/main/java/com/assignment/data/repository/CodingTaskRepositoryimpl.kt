package com.assignment.data.repository

import android.util.Log
import com.assignment.data.api.ICodingTaskApi
import com.assignment.data.dto.JokeDto
import com.assignment.data.mapper.dtotoentity.map
import com.assginment.domain.common.ResultState
import com.assginment.domain.entity.JokeEntity
import com.assginment.domain.repository.CodingTaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import java.net.UnknownHostException

class CodingTaskRepositoryimpl (val api: ICodingTaskApi) : CodingTaskRepository {

    override fun getJoke(): Flow<ResultState<JokeEntity>> {
        Log.e("frepository>>",">>>.")

        return flow {
            emit(ResultState.loading(null))
          emit( ResultState.success( remoteJokeCall()))
        }.catch { e ->
            when (e) {
                is SocketException -> {
                    emit(
                        ResultState.error(
                            "Request timeout. Please check your internet connection.",
                            null
                        )
                    )
                }
                is HttpException -> {
                    val httpCode = e.code()
                    emit(
                        ResultState.error(
                            when (httpCode) {
                                401 -> "User Session Expired! Please logout and re-login to your account."
                                else -> "Oops..! Something went wrong!\nPlease try again after sometime.\n(Error Code: $httpCode)"
                            },
                            null
                        )
                    )
                }
                is UnknownHostException, is IOException -> {
                    emit(
                        ResultState.error(
                            "Unable to reach server. Please check your internet connection.",
                            null
                        )
                    )
                }
                else -> {
                    emit(
                        ResultState.error(
                            "Unable to understand server's response. Please try again after sometime.",
                            null
                        )
                    )
                }
            }
        }
    }

   suspend  fun remoteJokeCall(): JokeEntity {
    val response=   api.getJoke()
      return JokeDto(response).map()


    }
}