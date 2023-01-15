package com.assginment.domain.entity

sealed class ErrorEntity {

    data class Error(val errorCode: Any?, val errorMessage: String? = "") : ErrorEntity()
}