package com.dovohmichael.fixerapp.data_remote.networking.history

import retrofit2.http.GET
import retrofit2.http.Query

interface HistoryRateService {

    @GET(value = "/fixer/timeseries")
    suspend fun fetchHistory(@Query("start_date") start_date:String,
                             @Query("end_date") end_date:String,
                             @Query("base") base:String,
                             @Query("symbols") symbols:String): HistoryRateApiModel
}