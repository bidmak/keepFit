package com.example.keepfit.ui.goal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepfit.Graph
import com.example.keepfit.data.entity.GoalData
import com.example.keepfit.data.repository.GoalDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GoalViewModel(
    private val goalDataRepository: GoalDataRepository = Graph.goalDataRepository
) : ViewModel() {

    private val _state = MutableStateFlow(GoalViewState())

    val state: StateFlow<GoalViewState>
        get() = _state

    suspend fun addGoal(goal: GoalData): Long {
        return  goalDataRepository.saveGoal(goal)
    }

    suspend fun updateGoal(goal: GoalData) {
        return  goalDataRepository.updateGoalData(goal)
    }

    suspend fun deleteGoal(goal: GoalData): Int {
        return  goalDataRepository.removeGoal(goal)
    }


    init {
        viewModelScope.launch {
            goalDataRepository.goals().collect{goals ->
                _state.value = GoalViewState(goals)
            }
        }
    }
}


data class GoalViewState(

    val goals: List<GoalData> = emptyList(),
)
