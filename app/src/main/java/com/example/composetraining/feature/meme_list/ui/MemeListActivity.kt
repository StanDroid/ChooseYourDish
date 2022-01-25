package com.example.composetraining.feature.meme_list.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.example.composetraining.core.BaseActivity
import com.example.composetraining.core.ui.base.style.BasicTheme
import com.example.composetraining.core.ui.memes.ItemList
import com.example.composetraining.feature.meal_categories.viewmodel.CategoriesViewModel
import com.example.composetraining.feature.meme_list.viewmodel.MemeListViewModel
import com.example.composetraining.feature.random_meal.viewmodel.RandomMealViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MemeListActivity : BaseActivity() {

    private val viewModel by lazy { getViewModel<MemeListViewModel>() }
    private val mealCategoriesViewModel by lazy { getViewModel<CategoriesViewModel>() }

    companion object {
        const val TAG = "MEMETAG"
    }

    @ExperimentalCoilApi
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

    @ExperimentalCoilApi
    @Composable
    fun MemeListScreen() {
        Log.e(TAG, "Call MemeListScreen started")
        ItemList(viewModel.memeList)
        {
            Toast.makeText(
                this,
                "Clicked ${it.name} && state = ${it.state}",
                Toast.LENGTH_SHORT
            ).show()
        }
        Log.e(TAG, "Call MemeListScreen finished")
//        val mealViewModel by mealViewModel.uiState
//        Log.e(TAG, "mealViewModel" + mealViewModel.toUiState().toString())

//        val categoriesViewModel by mealCategoriesViewModel.categories.observeAsState()
//        Log.e(TAG, "categoriesViewModel:" + categoriesViewModel?.toUiState().toString())
    }
}