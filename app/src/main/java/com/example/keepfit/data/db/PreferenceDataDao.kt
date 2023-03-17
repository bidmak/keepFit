package com.example.keepfit.data.db

import androidx.room.*
import com.example.keepfit.data.entity.PreferenceData
import kotlinx.coroutines.flow.Flow

@Dao
interface PreferenceDataDao {

    @Query("SELECT * FROM preferences")
    fun getPreference(): Flow<List<PreferenceData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(preference: PreferenceData)

    @Update
    suspend fun update(preference: PreferenceData)
}