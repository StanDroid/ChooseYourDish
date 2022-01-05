package com.example.composetraining.feature

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import com.example.composetraining.core.BaseActivity
import com.example.composetraining.core.navigation.NavigationSystem
import com.example.composetraining.core.ui.base.style.BasicTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    companion object {
        const val TAG = "MainActivityTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate called")
        setContent {
            BasicTheme{
                NavigationSystem()
            }
        }
    }
}
