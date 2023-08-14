package com.example.lessons.domain.usecases

import com.example.lessons.domain.LessonsRepository

class GetLessonsUseCaseImpl(private val repository: LessonsRepository) : GetLessonsUseCase {

    override suspend fun execute() = repository.getLessons()
}