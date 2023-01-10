package com.dovohmichael.fixerapp.presentation_converter


data class CurrencyListModel(
    val items: List<CurrencyListItemModel> = listOf(),
)

data class CurrencyListItemModel(
    val iso: String,
    val name: String
)