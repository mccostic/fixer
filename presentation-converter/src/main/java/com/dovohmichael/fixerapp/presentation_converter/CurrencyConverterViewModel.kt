package com.dovohmichael.fixerapp.presentation_converter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dovohmichael.fixerapp.domain.usecase.GetCurrenciesUseCase
import com.dovohmichael.fixerapp.domain.usecase.GetRateUseCase
import com.dovohmichael.fixerapp.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val useCase: GetCurrenciesUseCase,
    private val getRateUseCase: GetRateUseCase,
    private val currencyConverter: CurrencyListConverter,
    private val rateConverter: RateConverter

) : ViewModel() {


    init {
        loadCurrencies()
    }
    private val _currencyListFlow =
        MutableStateFlow<UiState<CurrencyListModel>>(UiState.Loading)
    val currencyListFlow: StateFlow<UiState<CurrencyListModel>> = _currencyListFlow

    private val _convertedRateFlow =
        MutableStateFlow<UiState<RateModel>>(UiState.Initial)
    val convertedRateFlow: StateFlow<UiState<RateModel>> = _convertedRateFlow

    private fun loadCurrencies() {
        viewModelScope.launch {
            useCase.execute(GetCurrenciesUseCase.Request)
                .map {
                    Log.d("CURRENCIES", it.toString())
                    currencyConverter.convert(it)
                }
                .collect {
                    if(it is UiState.Error){
                        val its = it.errorMessage
                        Log.d("CURRENCIESEROR", its)
                    }
                    _currencyListFlow.value = it
                }
        }
    }

    fun onCurrencyChanged(baseCurrencySymbol: String, baseAmount: String, targetCurrencySymbol: String) {

        if(baseCurrencySymbol.isNotEmpty() && targetCurrencySymbol.isNotEmpty() && baseAmount.toDoubleOrNull()!=null)
        convert(baseCurrencySymbol = baseCurrencySymbol, targetCurrencySymbol = targetCurrencySymbol, baseAmount = baseAmount.toDouble(),
            date = "2023-01-09")
    }

    fun convert(baseCurrencySymbol: String, targetCurrencySymbol: String, baseAmount: Double,date:String) {
        viewModelScope.launch {
            getRateUseCase.execute(GetRateUseCase.Request(base = baseCurrencySymbol,target=targetCurrencySymbol,date=date))
                .map {
                    Log.d("convert_currency", it.toString())
                    rateConverter.convert(it)
                }
                .collect {
                    if(it is UiState.Error){
                        val its = it.errorMessage
                        Log.d("convertERRR", its)
                    }
                    _convertedRateFlow.value = it
                }
        }
    }

    /*fun updateInteraction(interaction: Interaction) {
        viewModelScope.launch {
            updateInteractionUseCase.execute(
                UpdateInteractionUseCase.Request(
                    interaction.copy(
                        totalClicks = interaction.totalClicks + 1
                    )
                )
            ).collect()
        }
    }*/
}