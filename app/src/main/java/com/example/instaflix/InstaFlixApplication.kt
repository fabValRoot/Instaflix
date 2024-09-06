package com.example.instaflix

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InstaFlixApplication: Application() {

    override fun onCreate() {
        super.onCreate()

    }
}
