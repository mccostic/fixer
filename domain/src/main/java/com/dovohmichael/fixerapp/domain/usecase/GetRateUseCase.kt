package com.dovohmichael.fixerapp.domain.usecase


import android.util.Log
import com.dovohmichael.fixerapp.domain.entity.Currency
import com.dovohmichael.fixerapp.domain.entity.Rate
import com.dovohmichael.fixerapp.domain.repository.CurrencyRepository
import com.dovohmichael.fixerapp.domain.repository.RateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRateUseCase @Inject constructor(
    configuration: Configuration,
    private val rateRepository: RateRepository,
) : UseCase<GetRateUseCase.Request,
        GetRateUseCase.Response>(configuration) {

    override fun process(request:Request): Flow<Response> =
        rateRepository.getRate(base = request.base, target = request.target, date = request.date)
            .map {
                list->
                Log.d("GetRateUseCase", list.toString())
                Response(list)
            }


    data class Request(val base:String,val target:String,val date:String) : UseCase.Request

    data class Response(
        val rate: List<Rate> = listOf(),
    ) : UseCase.Response
}