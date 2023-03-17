package com.example.keepfit.data.repository

import com.example.keepfit.data.db.GoalDataDao
import com.example.keepfit.data.entity.GoalData
import kotlinx.coroutines.flow.Flow

/**
 *  Data Repository for [GoalData]
 */
class GoalDataRepository(
    private val goalDataDao: GoalDataDao
) {
    /**
     *  Flow containing list of [GoalData]
     */
    fun goals(): Flow<List<GoalData>> {
        return goalDataDao.goals()
    }

    /**
     *  To add [GoalData] to the DataBase
     */
    suspend fun saveGoal(goal: GoalData) = goalDataDao.insert(goal)

    /**
     *  To delete [GoalData] from the DataBase
     */
    suspend fun removeGoal(goal: GoalData) = goalDataDao.delete(goal)


    /**
     *  To update [GoalData] in the DataBase
     */
    suspend fun updateGoalData(goal: GoalData) = goalDataDao.update(goal)

}
