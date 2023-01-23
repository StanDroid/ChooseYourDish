package com.cyd.feature

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cyd.feature.random_meal.RandomMealScreen
import com.cyd.feature.random_meal.viewmodel.RandomMealUiState
import com.cyd.ui.view.base.MealScaffold
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RandomMealScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun noMealsText_shows() {
        composeTestRule.setContent {
            MealScaffold(topBarText = "") {
                RandomMealScreen(uiState = RandomMealUiState.NoRandomMeal(false, emptyList()))
            }
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(com.cyd.ui.R.string.no_random_meal))
            .assertIsDisplayed()
    }

    @Test
    fun noMealsText_shows1() {
        composeTestRule.setContent {
            MealScaffold(topBarText = "") {
                RandomMealScreen(uiState = RandomMealUiState.NoRandomMeal(false, emptyList()))
            }
        }
        val onNodeWithText =
            composeTestRule.onNodeWithText(composeTestRule.activity.getString(com.cyd.ui.R.string.go_to_categories))
        onNodeWithText.assertIsDisplayed()
        onNodeWithText.performClick()
    }
}