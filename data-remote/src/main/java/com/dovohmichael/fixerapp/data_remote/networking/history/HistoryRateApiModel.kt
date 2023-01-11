package com.dovohmichael.fixerapp.data_remote.networking.history

import com.squareup.moshi.Json

data class HistoryRateApiModel(@Json(name = "base") val base :String,
                               @Json(name = "end_date") val endDate:String,
                               @Json(name = "start_date") val startDate:String,
                               @Json(name = "timeseries") val timeseries :Boolean,
                               @Json(name = "rates") val rates:Map<String,Map<String,Double>>,
                               @Json(name = "success") val success:Boolean,)