package com.dovohmichael.fixerapp.presentation_converter

import android.content.Context
import android.util.Log
import com.dovohmichael.fixerapp.domain.usecase.GetCurrenciesUseCase
import com.dovohmichael.fixerapp.domain.usecase.GetRateUseCase
import com.dovohmichael.fixerapp.presentation_common.state.CommonResultConverter

import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RateConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetRateUseCase.Response, List<RateModel>>() {

    override fun convertSuccess(data: GetRateUseCase.Response): List<RateModel> {

        return data.rate.map {
            RateModel(base = it.base, target = it.target,date=it.date,
                timestamp = it.timestamp, rate = it.rate)
        }


    }
}