package com.example.hiltcoroutinesflow.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CoreBaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}