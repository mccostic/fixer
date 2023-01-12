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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
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
        MutableStateFlow<UiState<List<RateModel>>>(UiState.Initial)
    val convertedRateFlow: StateFlow<UiState<List<RateModel>>> = _convertedRateFlow

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
    private fun getDate(minusDays:Long =0): String {
        val zoneId = org.threeten.bp.ZoneId.systemDefault()
        return org.threeten.bp.LocalDate.now(zoneId).minusDays(minusDays).toString()
    }

    fun onCurrencyChanged(baseCurrencySymbol: String, baseAmount: String, targetCurrencySymbol: String) {
        convert(baseCurrencySymbol = baseCurrencySymbol, targetCurrencySymbol = targetCurrencySymbol, baseAmount = baseAmount.toDouble(),
            date = getDate())
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


                    Log.d("convert", it.toString())
                   //_convertedRateFlow.value = it
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