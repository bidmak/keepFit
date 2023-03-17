package com.example.keepfit.data.repository

import com.example.keepfit.data.db.PreferenceDataDao
import com.example.keepfit.data.entity.PreferenceData
import kotlinx.coroutines.flow.Flow

class PreferenceRepository(
    private val preferenceDataDao: PreferenceDataDao
) {

    fun preferences(): Flow<List<PreferenceData>>{
        return preferenceDataDao.getPreference()
    }

    suspend fun savePreference(preference: PreferenceData) = preferenceDataDao.insert(preference)
}