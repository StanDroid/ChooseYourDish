package com.example.composetraining

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetraining.ui.theme.ComposeTrainingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = "TESD")
//            ComposeTrainingTheme {
//                 A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    val scrollState = rememberLazyListState()
//                    LazyColumn(state = scrollState) {
//                        items(100) {
//                            Greeting("Android $it")
//                        }
//                    }
//                }
//            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Row(
        modifier = Modifier
            .padding(all = 8.dp)
            .clickable(onClick = {})
            .fillMaxWidth()
            .border(border = BorderStroke(1.dp, color = Color.Black)),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "logo",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .align(CenterVertically)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.align(CenterVertically)
        ) {
            Text(
                text = "Hello $name!",
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "2 Hello ",
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

@Preview()
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    ComposeTrainingTheme {
        Greeting("Android")
    }
}