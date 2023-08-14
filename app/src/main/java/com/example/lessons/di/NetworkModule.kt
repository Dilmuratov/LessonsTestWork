package com.example.lessons.di

import com.example.lessons.data.local.Constants
import com.example.lessons.data.network.LessonApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<LessonApi> {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

        val retrofit = Retrofit.Builder().baseUrl(Constants.baseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(LessonApi::class.java)

        api
    }
}