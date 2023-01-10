package com.dovohmichael.fixerapp.data_repository.repository



import android.util.Log
import com.dovohmichael.fixerapp.data_repository.data_source.local.LocalCurrencyDataSource
import com.dovohmichael.fixerapp.data_repository.data_source.remote.RemoteCurrencyDataSource
import com.dovohmichael.fixerapp.domain.entity.Currency
import com.dovohmichael.fixerapp.domain.entity.Rate
import com.dovohmichael.fixerapp.domain.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val remoteCurrencyDataSource: RemoteCurrencyDataSource,
    private val localCurrencyDataSource: LocalCurrencyDataSource
) : CurrencyRepository {

    override fun getCurrencies(): Flow<List<Currency>> =
        localCurrencyDataSource.getCurrencies().onEach {

            if (it.isEmpty()) {

                remoteCurrencyDataSource.getCurrencies().onEach { it2 ->
                    localCurrencyDataSource.addCurrencies(it2)
                }.collect()
            }


        }
}

        /*return remoteCurrencyDataSource.getCurrencies().onEach {
            localCurrencyDataSource.addCurrencies(it)
        }*/
