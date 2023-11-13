package com.hellguy39.minor_thing.presentation.navigation

sealed class Screen(val route: String) {

    data object Login: Screen("login")

    data object Register: Screen("register")

    data object Timetable: Screen("timetable")

    data object EditStudyDay: Screen("edit_study_day")

}
