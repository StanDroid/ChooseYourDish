package com.cyd.feature.random_meal

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.RoundedCornersTransformation
import com.cyd.base.extension.ifNotNullOrEmpty
import com.cyd.base.model.RandomMeal
import com.cyd.ui.view.base.AnnotatedClickableText
import com.cyd.ui.view.base.ProgressAsyncImage
import com.cyd.ui.view.base.RandomMealScreenConstants
import com.cyd.ui.view.base.style.BasicTheme

@Composable
fun RandomMealView(
    model: RandomMeal,
    onLoadNextRandomMeal: () -> Unit = {},
    onClickGoToCategories: () -> Unit = {},
    onClickGoToIngredients: () -> Unit = {},
    onClickGoToFavorites: () -> Unit = {},
    onClickGoToMealDetails: (Pair<String, String>) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        NavigateButtonsView(onClickGoToIngredients, onClickGoToCategories, onClickGoToFavorites)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Card(
                modifier = Modifier
                    .verticalScroll(rememberScrollState(0))
                    .fillMaxWidth()
                    .animateContentSize(animationSpec = tween(100)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SurpriseMeView(
                        Modifier.align(Alignment.CenterHorizontally),
                        onLoadNextRandomMeal
                    )
                    ProgressAsyncImage(
                        model = model.strMealThumb,
                        modifier = Modifier
                            .clickable { onClickGoToMealDetails.invoke(model.idMeal to model.strMeal) }
                            .height(200.dp)
                            .fillMaxWidth(),
                        withLoadingIndicator = false,
                        transformation = RoundedCornersTransformation(8f)
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
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = stringResource(id = R.string.area_s, model.strArea),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = stringResource(id = R.string.category_s, model.strCategory),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        if (expanded) {
                            Text(
                                modifier = Modifier.clickable { expanded = !expanded },
                                text = stringResource(R.string.instructions),
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(
                                modifier = Modifier
                                    .clickable { expanded = !expanded }
                                    .padding(bottom = 8.dp),
                                text = model.strInstructions,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        } else {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .padding(bottom = 8.dp)
                                    .clickable { expanded = !expanded },
                                text = stringResource(R.string.click_to_see_instructions),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                        model.strYoutube.ifNotNullOrEmpty {
                            AnnotatedClickableText(
                                str = stringResource(R.string.see_on_youtube),
                                link = it,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                        model.strSource.ifNotNullOrEmpty {
                            AnnotatedClickableText(
                                str = stringResource(R.string.original_post),
                                link = it,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NavigateButtonsView(
    onClickGoToIngredients: () -> Unit,
    onClickGoToCategories: () -> Unit,
    onClickGoToFavorites: () -> Unit
) {
    Column {
        Row {
            Button(
                onClick = { onClickGoToIngredients.invoke() },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .testTag(RandomMealScreenConstants.GO_TO_INGREDIENTS)
            ) {
                Text(
                    text = stringResource(R.string.go_to_ingredients),
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Button(
                onClick = { onClickGoToCategories.invoke() },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .testTag(RandomMealScreenConstants.GO_TO_CATEGORIES)
            ) {
                Text(
                    text = stringResource(R.string.go_to_categories),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
        Row {
            Button(
                onClick = { onClickGoToFavorites.invoke() },
                modifier = Modifier
                    .weight(1f)
                    .testTag(RandomMealScreenConstants.GO_TO_FAVORITES)
            ) {
                Text(
                    text = stringResource(R.string.favorites),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
private fun SurpriseMeView(modifier: Modifier, onLoadNextRandomMeal: () -> Unit) {
    Row(
        modifier = modifier
            .clickable { onLoadNextRandomMeal.invoke() }
            .padding(16.dp),
    ) {
        RotateIcon()
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(R.string.tap_on_card_title),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun RotateIcon() {
    val angle by rememberInfiniteTransition(label = "")
        .animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 1000,
                    delayMillis = 1500,
                    easing = FastOutSlowInEasing
                ),
                repeatMode = RepeatMode.Restart
            ), label = ""
        )
    Icon(
        modifier = Modifier
            .rotate(angle)
            .size(24.dp),
        imageVector = Icons.Filled.Refresh,
        contentDescription = "Refresh"
    )
}


@Preview(showBackground = true)
@Composable
fun RandomMealViewPreview() {
    BasicTheme {
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
}
