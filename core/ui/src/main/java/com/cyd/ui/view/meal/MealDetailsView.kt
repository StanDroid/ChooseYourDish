package com.cyd.ui.view.meal

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.cyd.base.extension.ifNotNullOrEmpty
import com.cyd.base.model.Meal
import com.cyd.base.model.MealIngredient
import com.cyd.ui.R
import com.cyd.ui.view.base.AnnotatedClickableText
import com.cyd.ui.view.base.ProgressAsyncImage

@Composable
fun MealDetailsView(
    meal: Meal,
    tapOnFavoritesAction: () -> Unit
) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState(0))
    ) {
        val model = ImageRequest.Builder(LocalContext.current)
            .data(meal.mealThumb)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
        val painter = rememberAsyncImagePainter(model)
        Box {
            Image(
                painter = painter,
                contentScale = ContentScale.FillWidth,
                contentDescription = "",
                modifier = Modifier.fillMaxWidth()
            )
            var isFavorite by remember { mutableStateOf(meal.isFavorite) }
            Icon(
                imageVector = if (meal.isFavorite)
                    Icons.Default.Favorite
                else
                    Icons.Default.FavoriteBorder,
                tint = if (meal.isFavorite) Color.Red else Color.White,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(32.dp)
                    .clickable(indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        isFavorite = !isFavorite
                        tapOnFavoritesAction.invoke()
                    }
                    .scale(animateFloatAsState(if (isFavorite) 1.2f else 1f, label = "").value))
        }
        Column(
            Modifier.padding(16.dp),
        ) {
            RowTitleText(stringResource(R.string.area), meal.area.orEmpty())

            LazyVerticalGrid(
                modifier = Modifier.heightIn(max = 2000.dp),
                columns = GridCells.Fixed(2),
                userScrollEnabled = false
            ) {
                items(meal.mealIngredients) { item ->
                    IngredientRow(item)
                }
            }
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = meal.instructions.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 19.sp
            )
            RowTitleText(stringResource(R.string.tags), meal.tags.orEmpty())
            meal.source.ifNotNullOrEmpty {
                AnnotatedClickableText(
                    modifier = Modifier.padding(top = 8.dp),
                    str = stringResource(R.string.original_post),
                    link = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            meal.youtube.ifNotNullOrEmpty {
                AnnotatedClickableText(
                    str = stringResource(R.string.see_on_youtube),
                    link = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun IngredientRow(
    mealIngredient: MealIngredient,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(8.dp)) {
        ProgressAsyncImage(
            model = mealIngredient.imageUrl,
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)

        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
        ) {
            Text(
                modifier = Modifier,
                text = mealIngredient.name,
                style = MaterialTheme.typography.titleSmall,
            )
            mealIngredient.measure?.let {
                Text(
                    modifier = Modifier,
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Composable
private fun RowTitleText(title: String, text: String) {
    if (text.isNotEmpty()) {
        Row(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = "$title:",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(start = 4.dp),
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailsScreenPreview() {
    MealDetailsView(
        Meal(
            category = "Category",
            area = "area",
            instructions = "instructions\ninstructions\n" +
                    "instructions",
            source = "source",
            tags = "tags",
            youtube = "youtube",
            isFavorite = true,
            mealIngredients = listOf(MealIngredient(name = "Lemon", measure = "2psc"))

        ),
        tapOnFavoritesAction = {}
    )
}