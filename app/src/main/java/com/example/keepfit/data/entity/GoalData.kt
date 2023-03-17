package com.example.keepfit.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "goals",
    indices = [
        Index("id", unique = true)
    ]
)
data class GoalData(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "goalName") val goalName: String,
    @ColumnInfo(name = "goalTarget") val goalTarget: Int
)
