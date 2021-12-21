package com.example.composetraining.feature.meme_list.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.composetraining.core.data.model.MemeModel
import com.example.composetraining.core.data.repository.MemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MemeListViewModel @Inject constructor(
    private val repository: MemeRepository
) : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    init {
        getMemeList()
    }
    val memeList = mutableStateListOf<MemeModel>()

//        private set

    fun getMemeList() {
        repository.getMemes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data }
            .toObservable()
            .flatMapIterable { it }
            .map {
                MemeModel(
                    name = it.name ?: "",
                    topText = it.topText ?: ""
                )
            }
            .toList()
            .subscribe(
                { list ->
                    memeList.addAll(list)
                    Log.i("TAG", "${list}")
                 },
                { error -> error.printStackTrace() }
            )
            .run {
                compositeDisposable.add(this)
            }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}