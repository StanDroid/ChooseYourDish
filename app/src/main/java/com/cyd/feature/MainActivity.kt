package com.cyd.feature

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import com.cyd.core.BaseActivity
import com.cyd.core.navigation.NavigationSystem
import com.cyd.ui.view.base.style.BasicTheme
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
