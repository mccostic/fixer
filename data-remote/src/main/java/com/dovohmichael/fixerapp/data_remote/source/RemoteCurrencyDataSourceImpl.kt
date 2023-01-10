package com.dovohmichael.fixerapp.data_remote.source

import com.dovohmichael.fixerapp.data_remote.networking.currency.CurrencyApiModel
import com.dovohmichael.fixerapp.data_remote.networking.currency.CurrencyService
import com.dovohmichael.fixerapp.data_remote.networking.currency.RateApiModel
import com.dovohmichael.fixerapp.data_repository.data_source.remote.RemoteCurrencyDataSource
import com.dovohmichael.fixerapp.domain.entity.Currency
import com.dovohmichael.fixerapp.domain.entity.Rate
import com.dovohmichael.fixerapp.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteCurrencyDataSourceImpl @Inject constructor(private val currencyService: CurrencyService) :
    RemoteCurrencyDataSource {

    override fun getCurrencies(): Flow<List<Currency>> = flow {
        emit(currencyService.getCurrencies())
    }.map { currencyApiModel ->
        convertCurrency(currencyApiModel)
    }.catch {
        throw UseCaseException.CurrencyException(it)
    }


    private fun convertCurrency(currencyApiModel: CurrencyApiModel): List<Currency> {
        return currencyApiModel.list.map { (k,v)->
            Currency(name=v, iso = k)
        }
    }



}