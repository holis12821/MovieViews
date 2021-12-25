package com.example.movieviews

import androidx.lifecycle.ViewModel
import com.example.movieviews.model.CuboidModel

class MainViewModel(
    private val cuboidModel: CuboidModel
): ViewModel() {

    fun getCircumference() = cuboidModel.getCircumference()
    fun getSurfaceArea() = cuboidModel.getSurfaceArea()
    fun getVolume() = cuboidModel.getVolume()

    fun save(w: Double, l: Double, h: Double) {
        cuboidModel.save(w, l, h)
    }

}