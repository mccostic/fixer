package com.dovohmichael.fixerapp.data_remote.source

import com.dovohmichael.fixerapp.data_remote.networking.history.HistoryRateApiModel
import com.dovohmichael.fixerapp.data_remote.networking.history.HistoryRateService
import com.dovohmichael.fixerapp.data_repository.data_source.remote.RemoteHistoryRateDataSource
import com.dovohmichael.fixerapp.domain.entity.HistoryRate
import com.dovohmichael.fixerapp.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteHistoryRateDataSourceImpl @Inject constructor(private val historyService: HistoryRateService) :
    RemoteHistoryRateDataSource {

    override fun getHistoryRate(base: String, target: String, startDate:String, endDate: String): Flow<List<HistoryRate>> = flow {
        emit(historyService.fetchHistory(base=base, start_date = startDate, end_date = endDate, symbols = target))
    }.map {
        convertHistoryRate(it,target)
    }.catch {
        throw UseCaseException.HistoryRateException(it)
    }




    private fun convertHistoryRate(historyRateApiModel: HistoryRateApiModel,target:String): List<HistoryRate> {
        val response = mutableListOf<HistoryRate>()
        if (historyRateApiModel.success) {
             historyRateApiModel.rates.map { rate ->
                //remove non-word characters

                for (k in rate.value) {
                    response.add(
                        HistoryRate(
                            base = historyRateApiModel.base,
                            date = rate.key,
                            target = k.key,
                            rate = k.value.toFloat()
                        )
                    )
                }


            }
        }
        return response
    }



}