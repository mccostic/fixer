package com.dovohmichael.fixerapp.data_remote.networking.currency

import com.squareup.moshi.Json

data class CurrencyApiModel(
    @Json(name = "symbols") val list: Map<String,String>,
    @Json(name = "success") val success: Boolean,
)