package com.dovohmichael.fixerapp.data_repository.data_source.local


import com.dovohmichael.fixerapp.domain.entity.Currency
import com.dovohmichael.fixerapp.domain.entity.Rate
import kotlinx.coroutines.flow.Flow

interface LocalCurrencyDataSource {

    fun getCurrencies(): Flow<List<Currency>>

    suspend fun addCurrencies(currencies: List<Currency>)

}