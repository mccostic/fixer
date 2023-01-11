package com.dovohmichael.fixerapp.domain.entity

import kotlinx.coroutines.flow.Flow

data class HistoryRate(val base:String,
                       val date:String,
                       val target:String,
                       val rate:Float)