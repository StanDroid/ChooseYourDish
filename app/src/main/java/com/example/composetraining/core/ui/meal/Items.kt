package com.example.composetraining.core.ui.meal

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.composetraining.core.data.model.mealdb.RandomMeal
import com.example.composetraining.feature.random_meal.viewmodel.HomeUiState

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RandomMealView(
    model: RandomMeal,
    onLoadNextRandomMeal: () -> Unit = {},
    onNavigateToCategories: () -> Unit = {}
) {
    var expandable by remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { onNavigateToCategories.invoke() },
            modifier = Modifier
                .align(Alignment.End)
                .padding(bottom = 16.dp)
        ) {
            Text(text = "Go to Categories")
        }
        Card(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .animateContentSize()
                .clickable { onLoadNextRandomMeal.invoke() },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                val painter = rememberImagePainter(data = model.strMealThumb,
                    builder = { /*transformations(CircleCropTransformation())*/ })
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painter,
                        contentScale = ContentScale.Crop,
                        contentDescription = "",
                        modifier = Modifier.size(250.dp)
                    )
                }
                Text(
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(bottom = 8.dp),
                    text = model.strMeal, style = MaterialTheme.typography.h5
                )
                Text(text = "Area: ${model.strArea}", style = MaterialTheme.typography.h6)
                Text(
                    text = "Category: ${model.strCategory}",
                    style = MaterialTheme.typography.subtitle1
                )
                if (expandable) {
                    Text(
                        modifier = Modifier.clickable { expandable = !expandable },
                        text = "Instructions: ${model.strInstructions}",
                        style = MaterialTheme.typography.subtitle2
                    )
                } else {
                    Text(
                        modifier = Modifier.clickable { expandable = !expandable },
                        text = "Click here to see instructions",
                        style = MaterialTheme.typography.subtitle2
                    )
                }
                Text(
                    text = "Source: ${model.strSource}",
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = "Youtube: ${model.strYoutube}",
                    style = MaterialTheme.typography.body2
                )
            }
        }
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
        )
    )
}


//@Preview(showBackground = true, name = "loading")
@Composable
fun NoRandomMealLoadingPreview() {
    NoRandomMealView(state = HomeUiState.NoRandomMeal(isLoading = true))
}

//@Preview(showBackground = true, name = "No data")
@Composable
fun NoRandomMealPreview() {
    NoRandomMealView(state = HomeUiState.NoRandomMeal(isLoading = false))
}