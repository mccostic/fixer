package com.dovohmichael.fixerapp.presentation_converter


data class RateModel(
    val base:String, val target:String, val date:String, val timestamp: Long,val rate:Double
)