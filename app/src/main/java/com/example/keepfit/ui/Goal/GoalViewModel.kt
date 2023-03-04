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
import com.example.keepfit.data.entity.GoalData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GoalViewModel: ViewModel() {
    private val _state = MutableStateFlow(GoalViewState())
    val state: StateFlow<GoalViewState>
        get() = _state

    init {
        val list = mutableListOf<GoalData>()
        for (x in 1..10){
            list.add(
                GoalData(
                    goalName = "Goal $x",
                    goalTarget = 3000 + (x *2000)
                 )
            )
        }
        viewModelScope.launch {
            _state.value = GoalViewState(
                goals = list
            )
        }
    }
}

data class GoalViewState(
    val goals: List<GoalData> = emptyList(),
)

@Composable
fun Edit(){
    IconButton(onClick = {  } ) {
        Icon(
            modifier = Modifier.padding(horizontal = 10.dp),
            imageVector = Icons.Default.Menu,
            contentDescription = "menu",
            tint = Color.Black)
    }
}