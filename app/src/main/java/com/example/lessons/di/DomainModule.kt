package com.example.lessons.di

import com.example.lessons.domain.usecases.GetLessonsUseCase
import com.example.lessons.domain.usecases.GetLessonsUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<GetLessonsUseCase> {
        GetLessonsUseCaseImpl(get())
    }
}