package com.dovohmichael.fixerapp.data_repository.data_source.remote


import com.dovohmichael.fixerapp.domain.entity.Currency
import com.dovohmichael.fixerapp.domain.entity.HistoryRate
import com.dovohmichael.fixerapp.domain.entity.Rate
import kotlinx.coroutines.flow.Flow

interface RemoteHistoryRateDataSource {

   fun getHistoryRate(base: String, target: String, startDate:String, endDate: String):Flow<List<HistoryRate>>

}