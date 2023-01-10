package com.dovohmichael.fixerapp.presentation_converter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dovohmichael.fixerapp.presentation_common.navigation.HistoryInput
import com.dovohmichael.fixerapp.presentation_common.navigation.NavRoutes

import com.dovohmichael.fixerapp.presentation_common.state.CommonScreen

import com.dovohmichael.fixerapp.presentation_common.state.UiState

@Composable
fun ConverterScreen(
    viewModel: CurrencyConverterViewModel,
    modifier:Modifier,
    navController: NavController
) {
    var targetCurrencySymbol by rememberSaveable { mutableStateOf("GHS") }
    var baseCurrencySymbol by rememberSaveable { mutableStateOf("USD") }
    var baseAmount by rememberSaveable { mutableStateOf("1") }

    var swapCurrency by rememberSaveable { mutableStateOf(false) }

    var rate by rememberSaveable {
        mutableStateOf("")
    }

    // val convertCurrency =   viewModel.convertedRateFlow.collectAsState()
    viewModel.convertedRateFlow.collectAsState().value.let {
        when (it) {
            is UiState.Initial -> {
                rate = ""
            }
            is UiState.Loading -> {
                rate =""
            }
            is UiState.Success -> {
                rate = if(baseAmount.isNotEmpty() && baseAmount.toDoubleOrNull()!=null)
                    String.format("%.3f",it.data.rate * baseAmount.toDouble())
                else "cannot convert type"
            }
            is UiState.Error->{
                rate = it.errorMessage
            }
        }
    }


    Column(modifier = Modifier.padding(24.dp, 0.dp)) {


        Spacer(modifier = Modifier.height(40.dp))


        viewModel.currencyListFlow.collectAsState().value.let { state ->
            CommonScreen(state = state) {
                    currencyListModel->
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    //base currency picker
                    CurrencyPicker(
                        modifier=Modifier.padding(2.dp),
                        readOnly = false,
                        enabled = true,
                        defaultSymbol = baseCurrencySymbol,
                        currencyListModel.items,
                        onSymbolSelected = {
                            baseCurrencySymbol = it
                            viewModel.onCurrencyChanged(
                                it,
                                baseAmount,
                                targetCurrencySymbol
                            )

                        }
                    )
                    //comparison arrow icon
                    IconButton(onClick = {
                        val temp= baseCurrencySymbol
                        baseCurrencySymbol = targetCurrencySymbol
                        targetCurrencySymbol = temp
                        swapCurrency = !swapCurrency



                        if(baseAmount.isNotEmpty() && targetCurrencySymbol.isNotEmpty() && baseCurrencySymbol.isNotEmpty()){
                            viewModel.convert(
                                baseCurrencySymbol,
                                targetCurrencySymbol,
                                baseAmount.toDouble(),
                                date="2023-01-09"
                            )
                        }
//                    currencyViewModel.setFromCurrency(targetCurrencySymbol)
//                    currencyViewModel.setToCurrency(baseCurrencySymbol)
//                    currencyViewModel.updateBase(newBaseAmount = String
//                        .format("%.3f", conversionRateState.result))
//                    currencyViewModel.updateConverted(baseAmount)

                    }) {
                        Icon(
                            imageVector = Icons.Filled.CompareArrows,
                            contentDescription = stringResource(R.string.compare_arrow_description),
                            modifier = Modifier.padding(8.dp),
                            tint = MaterialTheme.colors.onSecondary
                        )
                    }
                    CurrencyPicker(
                        modifier=Modifier.padding(2.dp),
                        readOnly = false,
                        enabled = true,
                        defaultSymbol = targetCurrencySymbol,
                        currencyListModel.items,
                        onSymbolSelected = {
                            targetCurrencySymbol = it
                            viewModel.onCurrencyChanged(
                                baseCurrencySymbol,
                                baseAmount,
                                it
                            )


                        }
                    )


                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))


        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CurrencyRateTextField(
                baseCurrencySymbol,
                modifier.weight(1.0f),
                readOnly = false,
                value = baseAmount,
                enabled = true,
                onBaseAmountChanged = { newBaseAmount ->
                    baseAmount = newBaseAmount
                    // currencyViewModel.updateBase(it)

                    if (newBaseAmount.isNotEmpty() && newBaseAmount.toDoubleOrNull()!=null) {
                        viewModel.convert(
                            baseCurrencySymbol,
                            targetCurrencySymbol,
                            newBaseAmount.toDouble(), date = "2023-01-09"
                        )


                    }
                }
            )
            Spacer(modifier = Modifier
                .height(16.dp)
                .weight(0.1f))
            //base currency picker
            CurrencyRateTextField(
                targetCurrencySymbol,
                modifier.weight(1.0f),
                readOnly = false,
                value = rate,
                /*value = data.convertedAmount,
*/
                enabled = false,

                onBaseAmountChanged = {

                }
            )

        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            elevation = null,
            onClick = {

                navController.navigate(NavRoutes.History.routeForHistory(HistoryInput(baseCurrencySymbol,targetCurrencySymbol)))



            },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primaryVariant,
                contentColor = Color.White
            )
        ) {
            //convert text
            Text(
                text = stringResource(R.string.detail_text),
                fontWeight = FontWeight.Bold, fontSize = 16.sp
            )
        }

        //vertical spacing between convert button and promo text
        Spacer(modifier = Modifier.height(16.dp))


    }

}

/*

@Composable
fun PostList(
    postListModel: PostListModel,
    onRowClick: (PostListItemModel) -> Unit,
    onAuthorClick: (PostListItemModel) -> Unit,
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item(postListModel.headerText) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = postListModel.headerText)
            }
        }
        items(postListModel.items) { item ->
            Column(modifier = Modifier
                .padding(16.dp)
                .clickable {
                    onRowClick(item)
                }) {
                ClickableText(text = AnnotatedString(text = item.authorName), onClick = {
                    onAuthorClick(item)
                })
                Text(text = item.title)
            }
        }
    }
}*/
