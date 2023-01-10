package com.dovohmichael.fixerapp.data_remote.networking.currency

import com.squareup.moshi.Json

data class RateApiModel(
    @Json(name = "rates") val rates: Map<String,Double>,
    @Json(name = "success") val success: Boolean,
    @Json(name = "base") val base: String,
    @Json(name = "date") val date: String,
    @Json(name = "historical") val historical: Boolean,
    @Json(name = "timestamp") val timestamp: Long,
)