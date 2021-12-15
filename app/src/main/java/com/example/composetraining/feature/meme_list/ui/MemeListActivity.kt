package com.example.composetraining.feature.meme_list.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composetraining.core.ui.ItemList
import com.example.composetraining.feature.meme_list.viewmodel.MemeListViewModel

class MemeListActivity : ComponentActivity() {

    private val viewModel = MemeListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItemList(viewModel.getMemeList())
            { Toast.makeText(this, "Clicked ${it.name}", Toast.LENGTH_SHORT).show() }
        }
    }
}
