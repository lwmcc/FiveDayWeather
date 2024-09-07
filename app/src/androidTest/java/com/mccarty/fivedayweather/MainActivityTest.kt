package com.mccarty.fivedayweather

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mccarty.fivedayweather.ui.components.MainScreen
import com.mccarty.fivedayweather.ui.theme.FiveDayWeatherTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class MainActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun firstTest() {
        composeTestRule.setContent {
            FiveDayWeatherTheme {

            }
        }
    }
}