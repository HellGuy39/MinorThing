package com.hellguy39.minor_thing.model

sealed class DayType {

    data object Sunday: DayType()

    data object Monday: DayType()

    data object Tuesday: DayType()

    data object Wednesday: DayType()

    data object Thursday: DayType()

    data object Friday: DayType()

    data object Saturday: DayType()

    data object None: DayType()

    fun toDisplayString(): String {
        return when(this) {
            Sunday -> "Sunday"
            Monday -> "Monday"
            Tuesday -> "Tuesday"
            Wednesday -> "Wednesday"
            Thursday -> "Thursday"
            Friday -> "Friday"
            Saturday -> "Saturday"
            None -> "None"
        }
    }

    override fun toString(): String {
        return when(this) {
            Sunday -> TAG_SUNDAY
            Monday -> TAG_MONDAY
            Tuesday -> TAG_TUESDAY
            Wednesday -> TAG_WEDNESDAY
            Thursday -> TAG_THURSDAY
            Friday -> TAG_FRIDAY
            Saturday -> TAG_SATURDAY
            None -> TAG_NONE
        }
    }

    companion object {

        private const val TAG_SUNDAY = "sunday"
        private const val TAG_MONDAY = "monday"
        private const val TAG_TUESDAY = "tuesday"
        private const val TAG_WEDNESDAY = "wednesday"
        private const val TAG_THURSDAY = "thursday"
        private const val TAG_FRIDAY = "friday"
        private const val TAG_SATURDAY = "saturday"
        private const val TAG_NONE = "none"

        fun from(s: String): DayType {
            return when(s) {
                TAG_SUNDAY -> Sunday
                TAG_MONDAY -> Monday
                TAG_TUESDAY -> Tuesday
                TAG_WEDNESDAY -> Wednesday
                TAG_THURSDAY -> Thursday
                TAG_FRIDAY -> Friday
                TAG_SATURDAY -> Saturday
                else -> None
            }
        }
    }
}
