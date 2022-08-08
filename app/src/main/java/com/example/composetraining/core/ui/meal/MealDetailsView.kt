package com.example.composetraining.core.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.composetraining.core.data.model.mealdb.Meal

@Composable
fun MealDetailsView(meal: Meal) {
    Column {
        Text(text = "MealDetailsView")
        Text(text = meal.meal)
        Text(text = meal.instructions)
    }
}