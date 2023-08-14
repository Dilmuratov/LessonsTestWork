package com.example.lessons.data.network

import com.example.lessons.data.models.LessonsResponseData
import retrofit2.Response
import retrofit2.http.GET

interface LessonApi {

    @GET("/test-api/lessons/")
    suspend fun getLessons(): Response<LessonsResponseData>
}