package com.example.keepfit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keepfit.data.entity.PreferenceData
import com.example.keepfit.data.repository.PreferenceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PreferenceViewModel(
    private val preferenceRepository: PreferenceRepository = Graph.preferenceRepository
): ViewModel() {
    private val _state = MutableStateFlow(PreferenceViewState())

    val state: StateFlow<PreferenceViewState>
        get() = _state

    suspend fun savePreference(preference: PreferenceData) {
        return preferenceRepository.savePreference(preference)
    }

    init {
        viewModelScope.launch {
            preferenceRepository.preferences().collect{preferences ->
                _state.value = PreferenceViewState(preferences)
            }
        }
    }


}

data class PreferenceViewState(
    val preferences: List<PreferenceData> = emptyList()
)