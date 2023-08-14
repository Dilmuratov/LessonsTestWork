package com.example.lessons.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lessons.data.models.Lesson

abstract class LessonsViewModel : ViewModel() {

    abstract val lessonsLiveData: LiveData<List<Lesson>>

    abstract suspend fun getLessons()
}