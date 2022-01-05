package com.example.composetraining.core.ui.meal

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.example.composetraining.core.data.model.mealdb.RandomMeal
import com.example.composetraining.feature.random_meal.viewmodel.HomeUiState

@Composable
fun RandomMealView(model: RandomMeal, onClick: () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .clickable { onClick.invoke() },
        contentAlignment = Alignment.Center,
    ) {
        val painter = rememberImagePainter(data = model.strMealThumb, builder = {
//            transformations(CircleCropTransformation())
        })
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = ""
        )
        Text(text = model.strMeal, style = MaterialTheme.typography.body1)
    }
}


@Composable
fun NoRandomMealView(state: HomeUiState) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {
            Text(
                text = "There is no random meal",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RandomMealViewPreview() {
    RandomMealView(
        model = RandomMeal(
            idMeal = "1",
            strMealThumb = "strMealThumb",
            strYoutube = "strYoutube",
            strSource = "strSource",
            strMeal = "name",
            strInstructions = "strInstructions",
            strCategory = "category",
            strArea = "strArea",
        ),
        {}
    )
}


@Preview(showBackground = true, name = "loading")
@Composable
fun NoRandomMealLoadingPreview() {
    NoRandomMealView(state = HomeUiState.NoRandomMeal(isLoading = true))
}

@Preview(showBackground = true, name = "No data")
@Composable
fun NoRandomMealPreview() {
    NoRandomMealView(state = HomeUiState.NoRandomMeal(isLoading = false))
}