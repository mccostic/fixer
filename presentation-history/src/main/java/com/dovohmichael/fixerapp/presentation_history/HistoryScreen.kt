package com.dovohmichael.fixerapp.presentation_history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dovohmichael.fixerapp.presentation_common.navigation.HistoryInput
import com.dovohmichael.fixerapp.presentation_common.state.CommonScreen

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel,
    input: HistoryInput
) {
    val base by rememberSaveable {
        mutableStateOf(input.baseCurrency)
    }
    val target by rememberSaveable {
        mutableStateOf(input.targetCurrency)
    }

    LaunchedEffect(key1 = base, key2 = target){
        viewModel.getHistoryRates(target=viewModel.getTargetCurrencies(target),endDate=viewModel.getDate(),startDate=viewModel.getDate(3), base = base)
    }


    viewModel.historyRatesFlow.collectAsState().value.let { state->
       CommonScreen(state = state) {
          historyRateListModel ->
           Box(contentAlignment = Alignment.Center) {
               Row {

                   LazyColumn(modifier = Modifier
                       .weight(1.0f)
                       .fillMaxWidth()) {
                       items(viewModel.filterHistoryCurrencies(historyRateListModel.items,target)) { history ->
                           HistoryItem(
                               history = history,
                               onEditClick = {

                               },
                               textColor = Color.White,
                               backgroundColor = MaterialTheme.colors.primary)
                       }
                   }
                   Spacer(modifier = Modifier.weight(0.1f))

                   LazyColumn(modifier = Modifier
                       .weight(1.0f)
                       .fillMaxWidth()
                   ) {
                       items(viewModel.filterOtherCurrencies(historyRateListModel.items,target)) { history ->
                           HistoryItem(
                               history = history,
                               onEditClick = {

                               },
                               textColor = Color.Black,
                               backgroundColor = MaterialTheme.colors.secondary)
                       }
                   }
               }

           }
       }
    }


}