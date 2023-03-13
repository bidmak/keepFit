package com.example.keepfit.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.keepfit.data.entity.ActivityData
import com.example.keepfit.data.entity.GoalData


@Database(
    entities = [GoalData::class, ActivityData::class],
    version = 3,
    exportSchema = false
)
abstract class KeepFitDataBase : RoomDatabase() {
    abstract fun goalDataDao(): GoalDataDao
    abstract fun activityDataDao(): ActivityDataDao
}