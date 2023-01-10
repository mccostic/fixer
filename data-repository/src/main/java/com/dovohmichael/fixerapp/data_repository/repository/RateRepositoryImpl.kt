package com.dovohmichael.fixerapp.data_repository.repository



import android.util.Log
import com.dovohmichael.fixerapp.data_repository.data_source.local.LocalCurrencyDataSource
import com.dovohmichael.fixerapp.data_repository.data_source.local.LocalRateDataSource
import com.dovohmichael.fixerapp.data_repository.data_source.remote.RemoteCurrencyDataSource
import com.dovohmichael.fixerapp.data_repository.data_source.remote.RemoteRateDataSource
import com.dovohmichael.fixerapp.domain.entity.Currency
import com.dovohmichael.fixerapp.domain.entity.Rate
import com.dovohmichael.fixerapp.domain.repository.CurrencyRepository
import com.dovohmichael.fixerapp.domain.repository.RateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RateRepositoryImpl @Inject constructor(
    private val remoteRateDataSource: RemoteRateDataSource,
    private val localRateDataSource: LocalRateDataSource
) : RateRepository {



    override fun getRate(base: String, target: String, date: String): Flow<List<Rate>> =
        localRateDataSource.getRate(base=base,target=target,date=date).onEach {

            if (it.isEmpty()) {

                remoteRateDataSource.getRate(base = base, target = target, date = date)
                    .onEach { it2 ->
                        Log.d("getRate",it2.toString())
                        localRateDataSource.addRate(it2)
                    }.collect()
            }
        }

    }
        /*return remoteCurrencyDataSource.getCurrencies().onEach {
            localCurrencyDataSource.addCurrencies(it)
        }*/
