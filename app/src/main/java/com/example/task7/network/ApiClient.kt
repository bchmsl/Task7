package com.example.task7.network

import com.example.task7.model.CoursesResponse
import com.example.task7.util.Constants.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET(ENDPOINT)
    suspend fun getCourses(): Response<CoursesResponse>
}