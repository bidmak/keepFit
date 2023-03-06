package com.example.keepfit

import android.app.Application

class KeepFitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(  this)
    }
}