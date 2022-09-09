package com.example.task7.domain.repositories

import com.example.task7.model.CoursesResponse
import com.example.task7.network.ResponseHandler
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getCourses(): Flow<ResponseHandler<CoursesResponse>>
}