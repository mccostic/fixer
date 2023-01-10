package com.dovohmichael.fixerapp.data_local.source



import com.dovohmichael.fixerapp.data_local.db.currency.CurrencyDao
import com.dovohmichael.fixerapp.data_local.db.currency.CurrencyEntity
import com.dovohmichael.fixerapp.data_local.db.rate.RateDao
import com.dovohmichael.fixerapp.data_local.db.rate.RateEntity
import com.dovohmichael.fixerapp.data_repository.data_source.local.LocalCurrencyDataSource
import com.dovohmichael.fixerapp.data_repository.data_source.local.LocalRateDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.dovohmichael.fixerapp.domain.entity.Currency
import com.dovohmichael.fixerapp.domain.entity.Rate

class LocalRateDataSourceImpl @Inject constructor(private val rateDao: RateDao) :
    LocalRateDataSource {




    override suspend fun addRate(rate: Rate) = rateDao.insertRate(RateEntity(
        rate = rate.rate,
        base = rate.base, target = rate.target,
        timestamp = rate.timestamp,
        date = rate.date,
        id = getId(base = rate.base, target = rate.target, date = rate.date)
    ))


    private fun getId(base: String, target: String, date: String) =  "${base}:${target}:${date}"

    override fun getRate(base: String, target: String, date: String): Flow<List<Rate>> =
        rateDao.getRate(id = getId(base = base, target = target, date = date)).map { posts ->
            posts.map {
                Rate(base=it.base,target=it.target, date=it.date, timestamp = it.timestamp, rate = it.rate)
            }
        }

}