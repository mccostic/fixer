package com.dovohmichael.fixerapp.domain.repository

import com.dovohmichael.fixerapp.domain.entity.HistoryRate
import kotlinx.coroutines.flow.Flow

interface HistoryRateRepository {
     fun getHistoryRate(base: String, target: String, startDate:String, endDate: String):Flow<List<HistoryRate>>
}