package com.example.lessons.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.lessons.app.App

class LocalStorage {
    companion object {
        val pref: SharedPreferences =
            App.context.getSharedPreferences("myPref", Context.MODE_PRIVATE)
    }
}