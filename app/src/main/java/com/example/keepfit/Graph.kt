package com.example.keepfit

import android.content.Context
import androidx.room.Room
import com.example.keepfit.data.db.KeepFitDataBase
import com.example.keepfit.data.repository.ActivityDataRepository
import com.example.keepfit.data.repository.GoalDataRepository

object Graph {
    private lateinit var database: KeepFitDataBase

    val goalDataRepository by lazy {
        GoalDataRepository(
            goalDataDao = database.goalDataDao()
        )
    }
    val activityDataRepository by lazy {
        ActivityDataRepository(
            activityDataDao = database.activityDataDao()
        )
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, KeepFitDataBase::class.java, "data.db")
            .build()
    }
}