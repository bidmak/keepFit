package com.example.keepfit.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepfit.Graph
import com.example.keepfit.data.entity.ActivityData
import com.example.keepfit.data.repository.ActivityDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ActivityViewModel(
    private val activityDataRepository: ActivityDataRepository = Graph.activityDataRepository
): ViewModel() {
    private val _state = MutableStateFlow(ActivityViewState())

    val state: StateFlow<ActivityViewState>
        get() = _state

    suspend fun saveActivity(activity: ActivityData): Long {
        return activityDataRepository.saveActivity(activity)
    }

    suspend fun deleteActivity(activity: ActivityData): Int {
        return activityDataRepository.removeActivity(activity)
    }

    suspend fun deleteAllActivity(activities: List<ActivityData>): Int {
        return activityDataRepository.removeAllActivities(activities)
    }


    init {
        viewModelScope.launch {
            activityDataRepository.activities().collect{activities ->
                _state.value = ActivityViewState(activities)
            }
        }
    }
}

data class ActivityViewState(
    val activities: List<ActivityData> = emptyList()
)