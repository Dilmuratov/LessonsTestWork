package com.example.lessons.data.repositories

import android.util.Log
import android.widget.Toast
import com.example.lessons.app.App
import com.example.lessons.data.models.Lesson
import com.example.lessons.data.network.LessonApi
import com.example.lessons.domain.LessonsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class LessonsRepositoryImpl(private val api: LessonApi) : LessonsRepository {

    override suspend fun getLessons(): Flow<List<Lesson>> = flow {
        val response = api.getLessons()
        if (response.isSuccessful) {
            emit(response.body()!!.lessons)
        } else {
            Toast.makeText(App(), "Somethings went wrong!", Toast.LENGTH_SHORT).show()
            Log.d("TTTT", "getLessons: ${response.message()}")
        }
    }.catch { it.printStackTrace() }
}