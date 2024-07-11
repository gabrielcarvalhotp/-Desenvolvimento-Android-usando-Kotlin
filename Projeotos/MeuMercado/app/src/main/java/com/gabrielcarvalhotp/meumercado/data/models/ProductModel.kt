package com.gabrielcarvalhotp.meumercado.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ProductModel(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val unit: String = "",
    val weight: Double = 0.0
): Parcelable {
}