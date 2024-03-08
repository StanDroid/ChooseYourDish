package com.cyd.feature

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cyd.ui.view.base.MealScaffold
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScaffoldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun topBar_shows() {
        val text = "test app bar text"
        composeTestRule.setContent {
            MealScaffold(text) {}
        }
        composeTestRule.onNodeWithText(text)
            .assertIsDisplayed()
    }
}