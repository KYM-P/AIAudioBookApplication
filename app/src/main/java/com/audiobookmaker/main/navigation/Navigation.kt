package com.audiobookmaker.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.audiobookmaker.main.ui.main.MainScreen

fun NavGraphBuilder.navigationGraph(
    navController: NavController
) {
    composable(route = MAIN_ROUTE) {
        MainScreen()
    }
}