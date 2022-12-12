package com.example.chooseyourdish.core

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

open class BaseActivity : ComponentActivity() {

    protected inline fun <reified T : ViewModel> getViewModel(): T {
        return ViewModelProvider(this).get(T::class.java)
    }

}