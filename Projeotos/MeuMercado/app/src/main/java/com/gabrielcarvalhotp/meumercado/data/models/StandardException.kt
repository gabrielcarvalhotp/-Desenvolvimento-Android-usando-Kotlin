package com.gabrielcarvalhotp.meumercado.data.models

import java.time.LocalDateTime
data class StandardException(
    val timestamp: LocalDateTime,
    val status: Int,
    val error: String,
    val message: String,
)