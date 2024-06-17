package entities

import kotlin.math.sqrt

class Rectangle {
    var width: Double = 0.0
    var height: Double = 0.0

    fun area(): Double {
        return width * height
    }

    fun perimeter(): Double {
        return 2 * (width + height)
    }

    fun diagonal(): Double {
        return sqrt(width * width + height * height)
    }
}