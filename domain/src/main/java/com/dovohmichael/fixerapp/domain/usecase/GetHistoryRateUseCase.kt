package com.dovohmichael.fixerapp.domain.usecase


import com.dovohmichael.fixerapp.domain.entity.HistoryRate
import com.dovohmichael.fixerapp.domain.repository.HistoryRateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetHistoryRateUseCase @Inject constructor(
    configuration: Configuration,
    private val historyRepository: HistoryRateRepository,
) : UseCase<GetHistoryRateUseCase.Request,
        GetHistoryRateUseCase.Response>(configuration) {

    override fun process(request:Request): Flow<Response> =
        historyRepository.getHistoryRate(
            base = request.base,
            target = request.target,
            startDate = request.startDate,
            endDate = request.endDate)
            .map {
                Response(it)
            }


    data class Request(val base:String,val target:String,val startDate:String,val endDate:String) : UseCase.Request

    data class Response(
        val historyRates: List<HistoryRate>,
    ) : UseCase.Response
}