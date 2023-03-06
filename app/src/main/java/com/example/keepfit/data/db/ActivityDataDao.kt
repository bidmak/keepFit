package com.example.keepfit.data.db

import androidx.room.*
import com.example.keepfit.data.entity.ActivityData
import kotlinx.coroutines.flow.Flow


@Dao
abstract class ActivityDataDao {

    @Query("SELECT * FROM activities WHERE date = :date")
    abstract fun getActivityDataByDate(date: String): ActivityData?

    @Query("SELECT steps FROM activities WHERE date = :date")
    abstract suspend fun getActivityStepsByDate(date: String): Int

    @Query("SELECT * FROM activities")
    abstract fun activities(): Flow<List<ActivityData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: ActivityData): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(entity: ActivityData)

    @Delete
    abstract suspend fun delete(entity: ActivityData): Int
}