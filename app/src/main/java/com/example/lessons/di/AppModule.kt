package com.example.lessons.di

import com.example.lessons.presentation.LessonsViewModel
import com.example.lessons.presentation.LessonsViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<LessonsViewModel> {
        LessonsViewModelImpl(
            getLessonsUseCase = get()
        )
    }
}