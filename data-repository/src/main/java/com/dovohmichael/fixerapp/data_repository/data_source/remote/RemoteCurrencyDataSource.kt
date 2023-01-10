package com.dovohmichael.fixerapp.data_repository.data_source.remote


import com.dovohmichael.fixerapp.domain.entity.Currency
import com.dovohmichael.fixerapp.domain.entity.Rate
import kotlinx.coroutines.flow.Flow

interface RemoteCurrencyDataSource {

    fun getCurrencies(): Flow<List<Currency>>

}