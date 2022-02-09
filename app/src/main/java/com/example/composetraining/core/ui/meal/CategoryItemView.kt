package com.example.composetraining.core.ui.meal

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.example.composetraining.core.data.model.mealdb.Category

@Composable
fun CategoryItemView(
    category: Category,
    onCategoryClick: (Category) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        Modifier
            .fillMaxWidth()

            .clickable { onCategoryClick.invoke(category) }
            .padding(start = 16.dp, end = 16.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Row(Modifier.fillMaxWidth()) {
            val painter = rememberImagePainter(
                data = category.thumb,
                builder = { transformations(RoundedCornersTransformation()) }
            )
            Image(
                painter = painter,
                contentScale = ContentScale.Inside,
                contentDescription = "",
                modifier = Modifier
                    .height(70.dp)
                    .width(70.dp)
            )
            Text(
                text = category.name,
                modifier = Modifier
                    .align(CenterVertically)
                    .background(MaterialTheme.colors.background)
                    .padding(16.dp)
                    .weight(1f),
                style = MaterialTheme.typography.subtitle1
            )
            if (expanded) {
                Icon(
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                        .padding(16.dp)
                        .align(CenterVertically),
                    painter = painterResource(id = android.R.drawable.arrow_up_float),
                    contentDescription = null // decorative element
                )
            } else {
                Icon(
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                        .padding(16.dp)
                        .align(CenterVertically),
                    painter = painterResource(id = android.R.drawable.arrow_down_float),
                    contentDescription = null // decorative element
                )
            }
        }
        if (expanded) {
            Text(text = category.description)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryItemViewPreview() {
    CategoryItemView(
        Category(
            id = "ID",
            name = "Name",
            "descr",
            "thumb"
        ), onCategoryClick = { })
}
