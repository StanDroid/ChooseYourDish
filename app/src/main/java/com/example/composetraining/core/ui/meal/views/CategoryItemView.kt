package com.example.composetraining.core.ui.meal.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.core.data.model.mealdb.Category

@Composable
fun CategoryItemView(
    category: Category,
    onCategoryClick: (Category) -> Unit
) {
    Text(
        text = category.name,
        modifier = Modifier
            .clickable { onCategoryClick.invoke(category) }
            .background(MaterialTheme.colors.background)
            .padding(8.dp)
            .fillMaxWidth(),
        style = MaterialTheme.typography.subtitle1
    )
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
