package com.example.composetraining.core.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.composetraining.core.data.model.memes.MemeModel

@ExperimentalCoilApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MemeItem(model: MemeModel, onClicked: (MemeModel) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 32.dp else 8.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = Color.Gray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp),
        onClick = {
            expanded = !expanded
            model.state = !model.state
            onClicked.invoke(model)
        }
    ) {
        Column() {
            val painter = rememberImagePainter(data = model.image, builder = {})
            Image(painter = painter, contentDescription = "")
            Text(
                modifier = Modifier.padding(
                    top = 32.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = extraPadding.coerceAtLeast(0.dp)
                ),
                text = "Hello ${model.name}!",
                color = Color.DarkGray,
                style = MaterialTheme.typography.h4
            )
        }
    }
}

@Composable
fun MemeItemDetailed(model: MemeModel) {
    Text(text = "Hello ${model.name}!")
}

@Composable
@Preview
private fun ShowMemeItem() {
    MemeItem(model = MemeModel("Test Text", "", ""), onClicked = {})
}