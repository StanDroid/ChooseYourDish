package com.example.composetraining.core.ui.meal

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.composetraining.core.data.model.mealdb.RandomMeal
import com.example.composetraining.feature.random_meal.viewmodel.HomeUiState

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RandomMealView(
    model: RandomMeal,
    onLoadNextRandomMeal: () -> Unit = {},
    onNavigateToCategories: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expanded) 32.dp else 8.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Column(
        modifier = Modifier
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
            modifier = Modifier
                .verticalScroll(rememberScrollState(0))
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(
                        300, easing = LinearOutSlowInEasing
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
                    modifier = Modifier.align(CenterHorizontally),
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
                            .align(CenterHorizontally)
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
                                .align(End)
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

@Composable
fun AnnotatedClickableText(str: String, link: String) {
    if (str.isEmpty()) return
    val annotatedLinkString: AnnotatedString = buildAnnotatedString {
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color(0xff64B5F6),
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline
            ), start = 0, end = str.length
        )

        // attach a string annotation that stores a URL to the text "link"
        addStringAnnotation(
            tag = "URL",
            annotation = link,
            start = 0,
            end = str.length - 1
        )
    }
    val uriHandler = LocalUriHandler.current
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        text = annotatedLinkString,
        onClick = {
            annotatedLinkString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { string ->
                    uriHandler.openUri(string.item)
                }
        }
    )
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