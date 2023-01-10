package com.dovohmichael.fixerapp.data_local.source



import com.dovohmichael.fixerapp.data_local.db.currency.CurrencyDao
import com.dovohmichael.fixerapp.data_local.db.currency.CurrencyEntity
import com.dovohmichael.fixerapp.data_repository.data_source.local.LocalCurrencyDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.dovohmichael.fixerapp.domain.entity.Currency
import com.dovohmichael.fixerapp.domain.entity.Rate

class LocalCurrencyDataSourceImpl @Inject constructor(private val currencyDao: CurrencyDao) :
    LocalCurrencyDataSource {

    override fun getCurrencies(): Flow<List<Currency>> = currencyDao.getCurrencies().map { posts ->
        posts.map {
            Currency(iso = it.iso, name = it.name)
        }
    }

    override suspend fun addCurrencies(currencies: List<Currency>) = currencyDao.insertCurrencies(currencies.map {
        CurrencyEntity(iso = it.iso, name =it.name)
    })




}