package com.gabrielcarvalhotp.meumercado.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class EstablishmentsModel(
    var id: Int = 0,
    var name: String = "",
    var address: String = "",
    var description: String = "",
    var deliveryTax: Double = 0.0,
    var minimumPurchase: Double = 0.0) : Parcelable
{}