package com.dovohmichael.fixerapp.data_repository.data_source.local


import com.dovohmichael.fixerapp.domain.entity.Currency
import com.dovohmichael.fixerapp.domain.entity.Rate
import kotlinx.coroutines.flow.Flow

interface LocalRateDataSource {

    suspend fun addRate(rate:Rate)

    fun getRate(base:String,target:String,date:String):Flow<List<Rate>>
}