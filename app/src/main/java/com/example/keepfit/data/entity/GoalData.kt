package com.example.keepfit.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "goals",
    indices = [
        Index("goalName", unique = true)
    ]
)
data class GoalData(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "goalName") val goalName: String,
    @ColumnInfo(name = "goalTarget") val goalTarget: Int
)
