package com.example.composetraining.core.ui.meal

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.composetraining.core.data.model.mealdb.RandomMeal
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
                .animateContentSize(animationSpec = tween(100))
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
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally),
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
                        text = model.strMeal,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5
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
            strMeal = "name very long",
            strInstructions = "strInstructions",
            strCategory = "category",
            strArea = "strArea",
        )
    )
}
