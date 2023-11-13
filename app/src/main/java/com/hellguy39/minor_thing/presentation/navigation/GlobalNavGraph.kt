package com.hellguy39.minor_thing.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hellguy39.minor_thing.presentation.screen.edit_study_day.EditStudyDayScreen
import com.hellguy39.minor_thing.presentation.screen.login.LoginScreen
import com.hellguy39.minor_thing.presentation.screen.register.RegisterScreen
import com.hellguy39.minor_thing.presentation.screen.timetable.TimetableScreen

@Composable
fun GlobalNavGraph() {

    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                navigateToTimetable = { navController.navigate(Screen.Timetable.route) },
                navigateToRegister = { navController.navigate(Screen.Register.route) }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                navigateBack = { navController.popBackStack() },
                navigateToTimetable = { navController.navigate(Screen.Timetable.route) }
            )
        }
        composable(Screen.EditStudyDay.route) {
            EditStudyDayScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Timetable.route) {
            TimetableScreen(
                navigateToEdit = { navController.navigate(Screen.EditStudyDay.route) }
            )
        }
    }
}