package com.hellguy39.minor_thing.ui.util

fun calculateScheduledSubjectTime(index: Int): String {
    return when(index) {
        0 -> "8:00 - 9.20"
        1 -> "9:40 - 11.00"
        2 -> "11:20 - 12.40"
        3 -> "13:00 - 14.20"
        4 -> "14:20 - 16.00"
        5 -> "16:10 - 17.30"
        6 -> "17:40 - 19.00"
        else -> "Overtime"
    }
}