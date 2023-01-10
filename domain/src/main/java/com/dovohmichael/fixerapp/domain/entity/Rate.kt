package com.dovohmichael.fixerapp.domain.entity

import java.sql.Timestamp

data class Rate(val base:String, val target:String, val date:String, val timestamp: Long,val rate:Double)