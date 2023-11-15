package com.hellguy39.minor_thing.presentation.navigation

const val ARG_ID_KEY = "id"
const val ARG_ID_EMPTY = -1

sealed class Screen(val route: String) {

    data object Login: Screen("login")

    data object Register: Screen("register")

    data object Timetable: Screen("timetable")

    data object EditStudyDay: Screen("edit_study_day/{$ARG_ID_KEY}") {
        fun passId(id: Int): String {
            return this.route.replace(oldValue = "{$ARG_ID_KEY}", newValue = id.toString())
        }
    }

}
