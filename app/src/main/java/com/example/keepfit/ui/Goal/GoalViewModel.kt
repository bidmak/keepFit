package com.example.keepfit.ui.Goal

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepfit.Graph
import com.example.keepfit.Graph.goalDataRepository
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

    suspend fun editGoal(goal: GoalData) {
        return  goalDataRepository.editCurrentGoal(goal)
    }

    suspend fun getGoal(goalName: String): GoalData? {
        return  goalDataRepository.getGoalByName(goalName)
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
