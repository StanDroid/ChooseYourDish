package com.example.chooseyourdish.feature

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import com.example.chooseyourdish.core.BaseActivity
import com.example.chooseyourdish.core.navigation.NavigationSystem
import com.example.chooseyourdish.core.ui.base.style.BasicTheme
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
