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
    fun removeActivity(activity: ActivityData) = activityDataDao.delete(activity)

    /**
     *  To update current [ActivityData] in the DataBase
     */
    suspend fun editCurrentActivity(activity: ActivityData) = activityDataDao.update(activity)

    /**
     *  To get [ActivityData] by date from the DataBase
     */
    fun getActivityDataByDate(date: String): ActivityData? = activityDataDao.getActivityDataByDate(date)

    /**
     *  To get [ActivityData] by date from the DataBase
     */
    fun getStepsByDate(date: String): Int = activityDataDao.getActivityStepsByDate(date)


}