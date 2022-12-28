package com.cyd.ui.view.meal

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.cyd.base.mealdb.MealItem
import com.cyd.ui.view.base.ProgressAsyncImage

@Composable
fun MealItemView(
    mealItem: MealItem,
    onMealClick: (MealItem) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable { onMealClick.invoke(mealItem) }
            .padding(start = 16.dp, end = 16.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Row(Modifier.fillMaxWidth()) {
            ProgressAsyncImage(
                model = mealItem.thumb,
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp),
                transformation = CircleCropTransformation()
            )
            Text(
                text = mealItem.name,
                modifier = Modifier
                    .align(CenterVertically)
                    .background(MaterialTheme.colors.background)
                    .padding(16.dp)
                    .weight(1f),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MealItemViewPreview() {
    MealItemView(mealItem =
        MealItem(
            id = "ID",
            name = "Name",
            "thumb"
        ), onMealClick = { })
}
