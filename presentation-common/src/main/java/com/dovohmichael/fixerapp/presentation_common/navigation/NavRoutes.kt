package com.dovohmichael.fixerapp.presentation_common.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val ROUTE_HOME = "home"
private const val ROUTE_HISTORY = "history?%s,%s"
private const val ARG_HISTORY_BASE_CURRENCY = "baseCurrency"
private const val ARG_HISTORY_TARGET_CURRENCY = "targetCurrency"

sealed class NavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {

    object Home : NavRoutes(ROUTE_HOME)

    object History : NavRoutes(
        route = String.format(ROUTE_HISTORY, "{$ARG_HISTORY_BASE_CURRENCY}","{$ARG_HISTORY_TARGET_CURRENCY}"),
        arguments = listOf(
            navArgument(ARG_HISTORY_BASE_CURRENCY) {
            type = NavType.StringType
        },  navArgument(ARG_HISTORY_TARGET_CURRENCY) {
                type = NavType.StringType
            })
    ) {

        fun routeForHistory(historyInput: HistoryInput) = String.format(ROUTE_HISTORY, historyInput.baseCurrency,historyInput.targetCurrency)

        fun fromEntry(entry: NavBackStackEntry): HistoryInput {
            return HistoryInput(entry.arguments?.getString(ARG_HISTORY_BASE_CURRENCY) ?: "",entry.arguments?.getString(
                ARG_HISTORY_TARGET_CURRENCY) ?: "")
        }
    }


}