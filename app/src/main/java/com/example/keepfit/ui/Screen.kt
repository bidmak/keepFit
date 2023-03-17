package com.example.keepfit.ui

const val GOAL_ID = "id"
const val GOAL_NAME = "goalName"
const val GOAL_TARGET = "goalTarget"

const val ACTIVITY_DATE = "date"
const val ACTIVITY_NAME = "goalName"
const val ACTIVITY_TARGET = "goalTarget"
const val ACTIVITY_STEP = "steps"

sealed class Screen(val route: String){
    object Activity: Screen(route = "activity")
    object Goal: Screen(route = "goal")
    object History: Screen(route = "history")


    object ActivityScreen: Screen(route = "activityScreen")
    object EditActivityScreen: Screen(route = "editActivityScreen" +
            "?date={$ACTIVITY_DATE}" +
            "&goalName={$ACTIVITY_NAME}" +
            "&goalTarget={$ACTIVITY_TARGET}" +
            "&steps={$ACTIVITY_STEP}"){
        fun passActivity(
            date: String,
            goalName: String,
            goalTarget: Int,
            steps: Int
        ): String {
            return "editActivityScreen" +
                    "?date=$date" +
                    "&goalName=$goalName" +
                    "&goalTarget=$goalTarget" +
                    "&steps=$steps"
        }
    }


    object GoalScreen: Screen(route = "goalScreen")
    object AddGoalScreen: Screen(route = "addGoalScreen")
    object EditGoalScreen: Screen(route = "editGoalScreen?id={$GOAL_ID}&goalName={$GOAL_NAME}&goalTarget={$GOAL_TARGET}"){
        fun passGoal(
            id: Int,
            goalName: String,
            goalTarget: String
        ): String {
            return "editGoalScreen?id=$id&goalName=$goalName&goalTarget=$goalTarget"
        }
    }

    object HistoryScreen: Screen(route = "historyScreen")
    object EditHistoryRecordScreen: Screen(route = "editHistoryRecordScreen" +
            "?date={$ACTIVITY_DATE}" +
            "&goalName={$ACTIVITY_NAME}" +
            "&goalTarget={$ACTIVITY_TARGET}" +
            "&steps={$ACTIVITY_STEP}"
    ){
        fun passActivity(
            date: String,
            goalName: String,
            goalTarget: Int,
            steps: Int
        ): String {
            return "editHistoryRecordScreen" +
                    "?date=$date" +
                    "&goalName=$goalName" +
                    "&goalTarget=$goalTarget" +
                    "&steps=$steps"
        }
    }
}
