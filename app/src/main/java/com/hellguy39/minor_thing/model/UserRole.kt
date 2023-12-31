package com.hellguy39.minor_thing.model

sealed class UserRole {

    data object Student: UserRole()

    data object Teacher: UserRole()

    data object None: UserRole()

    override fun toString(): String {
        return when(this) {
            None -> NONE_TAG
            Student -> STUDENT_TAG
            Teacher -> TEACHER_TAG
        }
    }

    companion object {

        private const val NONE_TAG = "None"
        private const val STUDENT_TAG = "Student"
        private const val TEACHER_TAG = "Teacher"

        fun fromString(s: String): UserRole {
            return when(s) {
                STUDENT_TAG -> Student
                TEACHER_TAG -> Teacher
                else -> None
            }
        }
    }
}
