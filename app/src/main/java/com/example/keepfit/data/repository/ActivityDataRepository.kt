package com.example.keepfit.data.repository

import com.example.keepfit.data.db.ActivityDataDao
import com.example.keepfit.data.entity.ActivityData
import kotlinx.coroutines.flow.Flow


/**
 *  Data Repository for [ActivityData]
 */
class ActivityDataRepository (
    private val activityDataDao: ActivityDataDao
        ){
    /**
     *  Flow containing list of [ActivityData]
     */
    fun activities(): Flow<List<ActivityData>> {
        return activityDataDao.activities()
    }

    /**
     *  To add [ActivityData] to the DataBase
     */
    suspend fun saveActivity(activity: ActivityData): Long = activityDataDao.insert(activity)

    /**
     *  To delete [ActivityData] from the DataBase
     */
    suspend fun removeActivity(activity: ActivityData): Int = activityDataDao.delete(activity)

    /**
     *  To delete [ActivityData] from the DataBase
     */
    suspend fun removeAllActivities(activities: List<ActivityData>): Int = activityDataDao.deleteAll(activities)


}