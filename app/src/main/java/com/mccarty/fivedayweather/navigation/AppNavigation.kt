package com.mccarty.fivedayweather.navigation

import androidx.navigation.NavHostController

class AppNavigation(private val navController: NavHostController) {
    fun navigateToDetails() {
        navController.navigate(DestinationKeys.DETAILS_SCREEN.name)
    }
}