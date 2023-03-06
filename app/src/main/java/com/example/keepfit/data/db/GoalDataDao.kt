package com.example.keepfit.data.db

import androidx.room.*
import com.example.keepfit.data.entity.GoalData
import kotlinx.coroutines.flow.Flow


@Dao
abstract class GoalDataDao {

    @Query("SELECT * FROM goals WHERE goalName = :goalName")
    abstract suspend fun getGoalDataByName(goalName: String): GoalData?

    @Query("SELECT * FROM goals")
    abstract fun goals(): Flow<List<GoalData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: GoalData): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(entities: Collection<GoalData>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(entity: GoalData)

    @Delete
    abstract suspend fun delete(entity: GoalData): Int
}