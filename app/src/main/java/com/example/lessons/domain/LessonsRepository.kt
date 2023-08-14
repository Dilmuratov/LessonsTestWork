package com.example.lessons.domain

import com.example.lessons.data.models.Lesson
import kotlinx.coroutines.flow.Flow

interface LessonsRepository {

    suspend fun getLessons(): Flow<List<Lesson>>
}