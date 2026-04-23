package com.cyd.feature.randommeal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cyd.ui.view.meal.ProgressLoadingView

@Composable
fun NoRandomMealView() {
    Box(Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(R.string.no_random_meal),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview(showBackground = true, name = "No data")
@Composable
fun NoRandomMealPreview() {
    NoRandomMealView()
}

@Preview(showBackground = true, name = "loading")
@Composable
fun NoRandomMealLoadingPreview() {
    ProgressLoadingView()
}
