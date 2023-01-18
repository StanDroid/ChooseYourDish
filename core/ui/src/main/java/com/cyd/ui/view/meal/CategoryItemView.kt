package com.cyd.ui.view.meal

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyd.base.mealdb.Category
import com.cyd.ui.view.base.CategoryListScreenConstants.CATEGORY_ITEM
import com.cyd.ui.view.base.ProgressAsyncImage

@Composable
fun CategoryItemView(
    category: Category,
    onCategoryClick: (Category) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        backgroundColor = Color.White,
        elevation = 2.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCategoryClick.invoke(category) }
                .padding(start = 16.dp, end = 16.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .testTag(CATEGORY_ITEM)
        ) {
            Row(Modifier.fillMaxWidth()) {
                ProgressAsyncImage(
                    model = category.thumb,
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
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        modifier = Modifier
                            .clickable { expanded = !expanded }
                            .padding(16.dp)
                            .align(CenterVertically),
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }
            if (expanded) {
                Text(text = category.description)
            }
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
