package com.example.keepfit.ui.Activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepfit.Graph
import com.example.keepfit.data.entity.ActivityData
import com.example.keepfit.data.repository.ActivityDataRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
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

    fun getActivity(date: String): ActivityData? {
        return activityDataRepository.getActivityDataByDate(date)
    }

    suspend fun updateActivity(activity: ActivityData) {
        return activityDataRepository.editCurrentActivity(activity)
    }
    fun getSteps(date: String): Int {
        return activityDataRepository.getStepsByDate(date)
    }

    var activityDataList = activityDataRepository.activities().stateIn(viewModelScope, started = SharingStarted.Eagerly, initialValue = listOf())


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