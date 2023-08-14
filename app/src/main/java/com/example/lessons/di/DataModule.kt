package com.example.lessons.di

import com.example.lessons.data.repositories.LessonsRepositoryImpl
import com.example.lessons.domain.LessonsRepository
import org.koin.dsl.module

val dataModule = module {
    single<LessonsRepository> {
        LessonsRepositoryImpl(
            api = get()
        )
    }
}