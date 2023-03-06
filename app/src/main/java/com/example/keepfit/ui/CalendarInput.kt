package com.example.keepfit.ui

data class CalendarInput(
    val day:Int,
    val toDos:List<String> = emptyList()
)