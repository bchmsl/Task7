package com.example.task7.ui.main

import com.example.task7.domain.repositories.MainRepository
import com.example.task7.model.CoursesResponse
import com.example.task7.network.ApiClient
import com.example.task7.network.ResponseHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val api: ApiClient) : MainRepository {

    override fun getCourses(): Flow<ResponseHandler<CoursesResponse>> =
        flow {
            val response = api.getCourses()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(ResponseHandler.Success(body))
                } else {
                    emit(ResponseHandler.Error(Throwable("Unexpected error occurred")))
                }
            } else {
                emit(ResponseHandler.Error(Throwable("Check your network connection")))
            }
        }
}