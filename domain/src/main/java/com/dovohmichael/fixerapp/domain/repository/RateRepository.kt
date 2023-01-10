package com.dovohmichael.fixerapp.domain.repository

import com.dovohmichael.fixerapp.domain.entity.Currency
import com.dovohmichael.fixerapp.domain.entity.Rate
import kotlinx.coroutines.flow.Flow

interface RateRepository {

     fun getRate(base:String,target:String,date:String): Flow<List<Rate>>
}