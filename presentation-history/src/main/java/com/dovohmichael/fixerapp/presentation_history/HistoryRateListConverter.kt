package com.dovohmichael.fixerapp.presentation_history

import android.content.Context
import com.dovohmichael.fixerapp.domain.usecase.GetCurrenciesUseCase
import com.dovohmichael.fixerapp.domain.usecase.GetHistoryRateUseCase
import com.dovohmichael.fixerapp.presentation_common.state.CommonResultConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HistoryRateListConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetHistoryRateUseCase.Response, HistoryRateListModel>() {

    override fun convertSuccess(data: GetHistoryRateUseCase.Response): HistoryRateListModel {

        return HistoryRateListModel(
            items = data.historyRates.map {
                HistoryRateListItemModel(
                   base = it.base,
                    target = it.target,
                    date = it.date,
                    rate = it.rate
                )
            },
        )
    }
}