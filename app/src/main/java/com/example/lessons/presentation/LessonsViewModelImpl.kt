package com.example.lessons.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lessons.data.models.Lesson
import com.example.lessons.domain.usecases.GetLessonsUseCase

class LessonsViewModelImpl(
    private val getLessonsUseCase: GetLessonsUseCase
) : LessonsViewModel() {

    private val _lessonsLiveData = MutableLiveData<List<Lesson>>()
    override val lessonsLiveData: LiveData<List<Lesson>>
        get() = _lessonsLiveData

    override suspend fun getLessons() {
        getLessonsUseCase.execute().collect {
            _lessonsLiveData.value = it
        }
    }
}