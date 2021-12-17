package com.example.composetraining.feature.meme_list.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composetraining.core.ui.ItemList
import com.example.composetraining.core.ui.style.BasicTheme
import com.example.composetraining.feature.meme_list.viewmodel.MemeListViewModel

class MemeListActivity : ComponentActivity() {

    private val viewModel = MemeListViewModel()

    companion object {
        const val TAG = "MEMETAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "Call onCreate started")
        setContent {
            BasicTheme {
                Log.e(TAG, "Call setContent started")
                var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
                if (shouldShowOnboarding) {
                    WelcomeScreen { shouldShowOnboarding = false }
                } else {
                    MemeListScreen()
                }
                Log.e(TAG, "Call setContent finished")
            }
        }
    }

    @Composable
    fun WelcomeScreen(onContinueClicked: () -> Unit) {
        Log.e(TAG, "Call WelcomeScreen started")
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onContinueClicked
            ) {
                Text(text = "Hello man!")
            }
        }
        Log.e(TAG, "Call WelcomeScreen finished")
    }

    @Composable
    fun MemeListScreen() {
        Log.e(TAG, "Call MemeListScreen started")
        ItemList(viewModel.getMemeList())
        {
            Toast.makeText(
                this,
                "Clicked ${it.name} && state = ${it.state}",
                Toast.LENGTH_SHORT
            ).show()
        }
        Log.e(TAG, "Call MemeListScreen finished")
    }
}