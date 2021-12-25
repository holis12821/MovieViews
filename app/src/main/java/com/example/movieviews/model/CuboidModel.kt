package com.example.movieviews.model


//this class implement builder pattern
class CuboidModel() {
    private var width = 0.0
    private var length = 0.0
    private var height = 0.0

    private fun getWidth(): Double = width
    private fun getLength(): Double = length
    private fun getHeight(): Double = height

    fun getVolume(): Double = (getWidth() * getLength() * getHeight())
    fun getSurfaceArea(): Double {
        val wl = getWidth() * getLength()
        val wh = getWidth() * getHeight()
        val lh = getLength() * getHeight()
        return 2 * (wl + wh + lh)
    }

    fun getCircumference(): Double = 4 * (getWidth() + getLength() + getHeight())

    fun save(width: Double, length: Double, height: Double) {
        this.width = width
        this.length = length
        this.height = height
    }
}


