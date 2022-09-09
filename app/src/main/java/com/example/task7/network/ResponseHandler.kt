package com.example.task7.network

import retrofit2.Response

sealed class ResponseHandler<T>(val isLoading: Boolean = false){
    class Success<T>(val data:T): ResponseHandler<T>()
    class Error<T>(val errorMessage:Throwable): ResponseHandler<T>()
    class Loading<T>(): ResponseHandler<T>(true)
}
