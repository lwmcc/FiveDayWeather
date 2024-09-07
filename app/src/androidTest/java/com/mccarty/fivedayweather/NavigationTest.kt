package com.mccarty.fivedayweather

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.mccarty.fivedayweather.navigation.AppNavigation
import com.mccarty.fivedayweather.navigation.DestinationKeys
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    // TODO: some gradle issues need to be fixed for this to run
    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavigation(navController = navController)
        }
    }

    @Test
    fun appNavigation_verifyStartDestination() {
        composeTestRule.onNodeWithContentDescription(DestinationKeys.MAIN_SCREEN.name)
            .assertIsDisplayed()
    }
}