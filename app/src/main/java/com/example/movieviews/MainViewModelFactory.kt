package com.example.movieviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieviews.model.CuboidModel

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val cuboidModel: CuboidModel
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T  = MainViewModel(cuboidModel) as T
}