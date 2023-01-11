package com.dovohmichael.fixerapp.data_repository.repository



import com.dovohmichael.fixerapp.data_repository.data_source.remote.RemoteHistoryRateDataSource
import com.dovohmichael.fixerapp.domain.entity.Currency
import com.dovohmichael.fixerapp.domain.entity.HistoryRate
import com.dovohmichael.fixerapp.domain.repository.HistoryRateRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class HistoryRateRepositoryImpl @Inject constructor(
    private val remoteHistoryRateDataSource: RemoteHistoryRateDataSource,
) : HistoryRateRepository {

    override fun getHistoryRate(base: String, target: String, startDate:String, endDate: String):
            Flow<List<HistoryRate>> = remoteHistoryRateDataSource.getHistoryRate(base=base, target=target, startDate=startDate, endDate=endDate)

}

        /*return remoteCurrencyDataSource.getCurrencies().onEach {
            localCurrencyDataSource.addCurrencies(it)
        }*/
