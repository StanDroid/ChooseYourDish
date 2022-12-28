package com.cyd.core.ui.meal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.cyd.R
import com.cyd.core.data.model.mealdb.Meal

@Composable
fun MealDetailsView(meal: Meal) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState(0))
            .background(Color.White)
    ) {
        val model = ImageRequest.Builder(LocalContext.current)
            .data(meal.mealThumb)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
        val painter = rememberAsyncImagePainter(model)
        Image(
            painter = painter,
            contentScale = ContentScale.FillWidth,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            Modifier.padding(16.dp),
        ) {
            RowTitleText(stringResource(R.string.area), meal.area.orEmpty())
            RowTitleText(stringResource(R.string.tags), meal.tags.orEmpty())

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = meal.instructions.orEmpty(),
                style = MaterialTheme.typography.body2,
                lineHeight = 19.sp
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(R.string.source),
                fontWeight = FontWeight.W600
            )
            Text(
                text = meal.source.orEmpty(),
                style = MaterialTheme.typography.body1
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(R.string.youtube),
                fontWeight = FontWeight.W600
            )
            Text(
                text = meal.youtube.orEmpty(),
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
private fun RowTitleText(title: String, text: String) {
    if (text.isNotEmpty()) {
        Row(modifier = Modifier.padding(top = 16.dp)) {
            Text(
                text = "$title: ",
                style = MaterialTheme.typography.body2
            )
            Text(
                text = text,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
@Preview
fun DetailsScreenPreview() {
    MealDetailsView(
        Meal(
            category = "Category",
            area = "area",
            instructions = "instructions \n instructions\n" +
                    " instructions",
            source = "source",
            tags = "tags",
            youtube = "youtube",

            )
    )
}