package com.cyd.feature.random_meal

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.RoundedCornersTransformation
import com.cyd.base.mealdb.RandomMeal
import com.cyd.ui.view.base.AnnotatedClickableText
import com.cyd.ui.view.base.ProgressAsyncImage
import com.cyd.ui.view.base.RandomMealScreenConstants

@Composable
fun RandomMealView(
    model: RandomMeal,
    onLoadNextRandomMeal: () -> Unit = {},
    onClickGoToCategories: () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Button(
            onClick = { onClickGoToCategories.invoke() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .testTag(RandomMealScreenConstants.GO_TO_CATEGORIES)
        ) {
            Text(text = stringResource(R.string.go_to_categories))
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Card(
                modifier = Modifier
                    .verticalScroll(rememberScrollState(0))
                    .fillMaxWidth()
                    .animateContentSize(animationSpec = tween(100))
                    .clickable { onLoadNextRandomMeal.invoke() },
                shape = RoundedCornerShape(16.dp)
            )   {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally),
                        text = stringResource(R.string.tap_on_card_title)
                    )
                    ProgressAsyncImage(
                        model = model.strMealThumb,
                        modifier = Modifier
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
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            modifier = Modifier.padding(bottom = 8.dp),
                            text = stringResource(id = R.string.category_s, model.strCategory),
                            style = MaterialTheme.typography.labelMedium
                        )
                        if (expanded) {
                            Text(
                                modifier = Modifier.clickable { expanded = !expanded },
                                text = stringResource(R.string.instructions),
                                style = MaterialTheme.typography.labelSmall
                            )
                            Text(
                                modifier = Modifier
                                    .clickable { expanded = !expanded }
                                    .padding(bottom = 8.dp),
                                text = model.strInstructions,
                                style = MaterialTheme.typography.labelSmall
                            )
                        } else {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .padding(bottom = 8.dp)
                                    .clickable { expanded = !expanded },
                                text = stringResource(R.string.click_to_see_instructions),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.DarkGray
                            )
                        }
                        AnnotatedClickableText(
                            stringResource(R.string.see_on_youtube),
                            model.strYoutube
                        )
                        AnnotatedClickableText(
                            stringResource(R.string.original_post),
                            model.strSource
                        )
                    }
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
