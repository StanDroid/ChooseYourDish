package com.cyd.ui.view.meal

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyd.base.model.Category
import com.cyd.ui.view.base.CategoryListScreenConstants.CATEGORY_ITEM
import com.cyd.ui.view.base.ProgressAsyncImage

@Composable
fun CategoryItemView(
    category: Category,
    onCategoryClick: (Category) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
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
                        .padding(16.dp)
                        .weight(1f),
                    style = MaterialTheme.typography.titleMedium
                )
                Icon(
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                        .padding(16.dp)
                        .align(CenterVertically),
                    imageVector = if (expanded)
                        Icons.Filled.KeyboardArrowUp
                    else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null
                )
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
