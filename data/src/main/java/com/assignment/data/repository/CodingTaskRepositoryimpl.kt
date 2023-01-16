package com.assignment.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.assignment.data.api.ICodingTaskApi
import com.assignment.data.dto.JokeDto
import com.assignment.data.mapper.dtotoentity.map
import com.assginment.domain.common.ResultState
import com.assginment.domain.entity.JokeEntity
import com.assginment.domain.repository.CodingTaskRepository
import com.assginment.domain.repository.LocalJokeListRepository
import com.assignment.data.constants.PrefConstants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vpcc.data.local.getPreference
import com.vpcc.data.local.putPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException
import java.net.UnknownHostException

class CodingTaskRepositoryimpl (val sharedPreferences: SharedPreferences, val api: ICodingTaskApi) : CodingTaskRepository ,LocalJokeListRepository {
    private  var jokeList =ArrayList<JokeEntity>();
    override fun getJoke(): Flow<ResultState<List<JokeEntity>>> {

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
    suspend fun getJokeData(): List<JokeEntity>{
      val jokeListData =  sharedPreferences.getPreference(PrefConstants.JOKE_LIST, "")
        if(jokeListData.isNullOrEmpty()){
        return    remoteJokeCall()
        }else{
            val myType = object : TypeToken<ArrayList<JokeEntity>>() {}.type

            jokeList= Gson().fromJson(jokeListData,myType)
        }
return jokeList
    }

   suspend  fun remoteJokeCall(): List<JokeEntity> {

    val response=   api.getJoke()
       jokeList.add(  JokeDto(response).map())
       if(jokeList.size>10)
           jokeList.removeAt(0)
       sharedPreferences.putPreference(PrefConstants.JOKE_LIST,Gson().toJson(jokeList))

       return jokeList

    }

    override fun getLocalJoke(): Flow<ResultState<List<JokeEntity>>> {
        return flow {
            emit(ResultState.loading(null))
            emit( ResultState.success( getJokeData()))
        }.catch { e ->


                    emit(
                        ResultState.error(
                            "Unable to understand server's response. Please try again after sometime.",
                            null
                        )
                    )


        }
    }
}