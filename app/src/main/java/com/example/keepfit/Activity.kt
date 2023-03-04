package com.example.keepfit

import androidx.lifecycle.MutableLiveData

data class Activity(
    var goalName: String = "Ambitious",
    var goalTarget: Int = 6000,
    var steps: Int = 4800,
    var percentage: Float = steps.toFloat()/goalTarget

)
