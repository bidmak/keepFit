package com.example.keepfit

data class CalendarInput(
    val day:Int,
    val toDos:List<String> = emptyList()
)