package com.dovohmichael.fixerapp.data_remote.source

import com.dovohmichael.fixerapp.data_remote.networking.currency.CurrencyApiModel
import com.dovohmichael.fixerapp.data_remote.networking.currency.CurrencyService
import com.dovohmichael.fixerapp.data_remote.networking.currency.RateApiModel
import com.dovohmichael.fixerapp.data_repository.data_source.remote.RemoteCurrencyDataSource
import com.dovohmichael.fixerapp.data_repository.data_source.remote.RemoteRateDataSource
import com.dovohmichael.fixerapp.domain.entity.Currency
import com.dovohmichael.fixerapp.domain.entity.Rate
import com.dovohmichael.fixerapp.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteRateDataSourceImpl @Inject constructor(private val currencyService: CurrencyService) :
    RemoteRateDataSource {


    override fun getRate(base: String, target: String, date: String): Flow<Rate> = flow {
        emit(currencyService.getRate(date = date, base = base, symbols=target))
    }.map { rateApiModel ->
        convertRate(rateApiModel,target)
    }.catch {
        throw UseCaseException.RateException(it)
    }

    private fun convertRate(rateApiModel: RateApiModel,target: String): Rate {
        return Rate(
            base = rateApiModel.base,
            target = target,
            date = rateApiModel.date,
            timestamp = rateApiModel.timestamp, rate = rateApiModel.rates.getValue(target))
    }



}