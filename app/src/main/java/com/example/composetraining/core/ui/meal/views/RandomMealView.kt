package com.example.composetraining.core.ui.meal.views

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.composetraining.core.data.model.mealdb.RandomMeal
import com.example.composetraining.core.navigation.NavScreen
import com.example.composetraining.core.ui.base.AnnotatedClickableText.AnnotatedClickableText

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RandomMealView(
    model: RandomMeal,
    onLoadNextRandomMeal: () -> Unit = {},
    onClickGoToCategories: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Button(
            onClick = { onClickGoToCategories.invoke() },
            modifier = Modifier
                .align(Alignment.End)
                .padding(bottom = 16.dp)
        ) {
            Text(text = "Go to Categories")
        }

        Card(
        modifier = Modifier
            .verticalScroll(rememberScrollState(0))
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable { onLoadNextRandomMeal.invoke() },
        shape = RoundedCornerShape(4.dp)
    ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                val painter = rememberImagePainter(
                    data = model.strMealThumb,
                    builder = { transformations(RoundedCornersTransformation()) }
                )
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Tap on Card to get a new one"
                )
                Image(
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 8.dp),
                        text = model.strMeal, style = MaterialTheme.typography.h5
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "Area: ${model.strArea}",
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "Category: ${model.strCategory}",
                        style = MaterialTheme.typography.subtitle1
                    )
                    if (expanded) {
                        Text(
                            modifier = Modifier.clickable { expanded = !expanded },
                            text = "Instructions:",
                            style = MaterialTheme.typography.subtitle2
                        )
                        Text(
                            modifier = Modifier
                                .background(Color.Green)
                                .clickable { expanded = !expanded }
                                .padding(bottom = 8.dp),
                            text = model.strInstructions,
                            style = MaterialTheme.typography.subtitle2
                        )
                    } else {
                        Text(
                            modifier = Modifier
                                .align(Alignment.End)
                                .padding(bottom = 8.dp)
                                .clickable { expanded = !expanded },
                            text = "Click here to see instructions",
                            style = MaterialTheme.typography.subtitle2,
                            color = Color.DarkGray
                        )
                    }
                    AnnotatedClickableText("See on Youtube", model.strYoutube)
                    AnnotatedClickableText("Original post", model.strSource)
                }
            }
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
