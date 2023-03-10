package com.example.keepfit.data.db

import androidx.room.*
import com.example.keepfit.data.entity.ActivityData
import kotlinx.coroutines.flow.Flow


@Dao
interface ActivityDataDao {

    @Query("SELECT * FROM activities WHERE date = :date")
    fun getActivityDataByDate(date: String): ActivityData?

    @Query("SELECT steps FROM activities WHERE date = :date")
    fun getActivityStepsByDate(date: String): Int

    @Query("SELECT * FROM activities")
    fun activities(): Flow<List<ActivityData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ActivityData): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: ActivityData)

    @Delete
    fun delete(entity: ActivityData): Int
}