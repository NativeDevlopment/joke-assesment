package com.assignment.data.api

import retrofit2.http.GET

interface ICodingTaskApi {
    @GET("/api")
    suspend fun getJoke():String

}
