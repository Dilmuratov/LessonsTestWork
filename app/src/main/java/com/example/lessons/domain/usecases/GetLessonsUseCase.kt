package com.example.lessons.domain.usecases

import com.example.lessons.data.models.Lesson
import kotlinx.coroutines.flow.Flow

interface GetLessonsUseCase {
    suspend fun execute(): Flow<List<Lesson>>
}