package com.example.keepfit.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "activities",
    indices = [
        Index("date", unique = true)
    ]
)
data class ActivityData(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "goalName") val goalName: String,
    @ColumnInfo(name = "goalTarget") val goalTarget: Int,
    @ColumnInfo(name = "steps") val steps: Int,
)

